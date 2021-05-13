package by.gstu.app.sender.telegram

import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.sender.Sender
import java.io.BufferedInputStream
import java.net.URL
import java.net.URLConnection

class TelegramMessageSender : Sender {

    companion object {
        var TELEGRAM_BOT_URL = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s"
    }

    override fun sendToPlatform(identifier: String,
                                message: String,
                                platform: Platform,
                                listener: BaseQueryResultListener) {
        val token: String = getToken(platform.data)
        if (token.isBlank()) {
            listener.onFailure("Something went wrong")
            return
        }
        val filledURL = String.format(TELEGRAM_BOT_URL, token, identifier, message)
        val url = URL(filledURL)
        val conn: URLConnection = url.openConnection()
        BufferedInputStream(conn.getInputStream())
        listener.onSuccess()
    }

    private fun getToken(data: String?): String {
        data?.let {
            val keys = it.split("=")
            if (keys.contains("token")) {
                return keys[keys.indexOf("token") + 1]
            }
        }
        return ""
    }
}