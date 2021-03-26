package it.acron.si.acornsysnotifer.commands

import com.andreapivetta.kolor.Color
import com.andreapivetta.kolor.Kolor
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.long
import fr.jcgay.notification.*
import it.acron.si.acornsysnotifer.data.NotificationLevel
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess


class NotificationCommand : CliktCommand() {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(javaClass)

    val title by option("-t", help = "The title of the notification.").required()
    val message by option("-m", help = "The message of the notification.").required()
    val level by option(
        "-l",
        help = "Level of the notification. Possible optiosn are 'info, warning, error'"
    ).default("info")
    val timeout by option("-to", help = "Length of the notification in microseconds").long().default(5000)

    override fun run() {
        val icon = NotificationCommand::class.java.getResource("/success.png")
        val application = Application.builder()
            .id("systemtray-example")
            .name(title)
            .icon(Icon.create(icon, "app"))
            .timeout(timeout)
            .build()

        val notifier = SendNotification()
            .setApplication(application)
            .setChosenNotifier("systemtray")
            .initNotifier()

        val notification = Notification.builder()
            .title(title)
            .message(message)
            .icon(Icon.create(icon, "ok"))
            .level(Notification.Level.ERROR)

        try {
            when (NotificationLevel.from(level)) {
                NotificationLevel.INFO -> notification.level(Notification.Level.INFO)
                NotificationLevel.WARNING -> notification.level(Notification.Level.WARNING)
                NotificationLevel.ERROR -> notification.level(Notification.Level.ERROR)
            }
        } catch (e: Exception) {
            println(Kolor.foreground("Loggin level must be on of 'info, warning, error'", Color.RED))
            exitProcess(1)
        }
        notifier.send(notification.build())
        notifier.close()
        exitProcess(0)
    }
}