package it.acron.si.acornsysnotifer.data

/**
 * Notification level of the system-tray notification
 * @property level String
 * @constructor
 */
enum class NotificationLevel(val level: String) {
    INFO("info"),
    WARNING("warning"),
    ERROR("error");


    companion object {
        fun from(findValue: String): NotificationLevel = NotificationLevel.values().first { it.level == findValue }
    }
}