package by.gstu.app.sender

import by.gstu.app.sender.telegram.TelegramMessageSender

abstract class PlatformSenderFactory {
    abstract fun createSender(): Sender

    companion object {
        fun getPlatformSender(kind: PlatformKind): PlatformSenderFactory {
            return when(kind) {
                PlatformKind.TELEGRAM -> TelegramSenderFactory()
            }
        }
    }
}

class TelegramSenderFactory : PlatformSenderFactory() {
    override fun createSender() = TelegramMessageSender()
}
