package by.gstu.app.sender

import by.gstu.app.sender.telegram.TelegramMessageSender
import by.gstu.app.sender.viber.ViberMessageSender

abstract class PlatformSenderFactory {
    abstract fun createSender(): Sender

    companion object {
        fun getPlatformSender(kind: PlatformKind): PlatformSenderFactory {
            return when(kind) {
                PlatformKind.TELEGRAM -> TelegramSenderFactory()
                PlatformKind.VIBER -> ViberSenderFactory()
            }
        }
    }
}

class TelegramSenderFactory : PlatformSenderFactory() {
    override fun createSender() = TelegramMessageSender()
}

class ViberSenderFactory : PlatformSenderFactory() {
    override fun createSender() = ViberMessageSender()
}
