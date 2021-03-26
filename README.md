# acron-system-tray-notifier

Kotlin shell app to run system-tray-notification on Windows computers

Run by `java -jar acron-system-tray-notifier.jar -t "Test title" -m "Test message"`
## CLI
Options:

-t TEXT     The title of the notification.

-m TEXT     The message of the notification.

-l TEXT     Level of the notification. Possible optiosn are 'info, warning,
error'

-to INT     Length of the notification in microseconds

-h, --help  Show this message and exit