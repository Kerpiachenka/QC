package by.gstu.app.sender.telegram

import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.sender.Sender
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.BufferedInputStream
import java.io.InputStream
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
        if (identifier.isBlank()) {
            listener.onFailure("Incorrect platform identifier")
            return
        }
        val token: String = getToken(platform.data)
        if (token.isBlank()) {
            listener.onFailure("Incorrect token")
            return
        }
        val filledURL = String.format(TELEGRAM_BOT_URL, token, identifier, message)
        val url = URL(filledURL)

        Observable.just(url)
                .subscribeOn(Schedulers.io())
                .map {send(url)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value -> print(value) }, // onNext
                        { error -> listener.onFailure(error.toString()) },    // onError
                        { listener.onSuccess() }                 // onComplete
                )
    }

    fun send(url: URL) {
        val conn: URLConnection = url.openConnection()
        val inputStream: InputStream = BufferedInputStream(conn.getInputStream())
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