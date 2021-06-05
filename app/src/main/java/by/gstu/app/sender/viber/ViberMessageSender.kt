package by.gstu.app.sender.viber

import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.sender.Sender
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class ViberMessageSender : Sender {
    companion object {
        var VIBER_BOT_URL = "https://chatapi.viber.com/pa/send_message"
        var MIN_API_VERSION = "\"min_api_version\":1,"
        var TRACKING_DATA = "\"tracking_data\":\"tracking data\","
        var TYPE = "\"type\":\"text\","
        var SENDER = "\"sender\":" + "{\"name\":\"Notifier\"},"
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

        Observable.just(VIBER_BOT_URL)
                .subscribeOn(Schedulers.io())
                .map {send(VIBER_BOT_URL, prepareBody(message, token, identifier))}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value -> print(value) }, // onNext
                        { error -> listener.onFailure(error.toString()) },    // onError
                        { listener.onSuccess() }                 // onComplete
                )
    }

    private fun prepareBody(message: String, token: String, userId: String): String {
        val body = String.format("{\"receiver\":\"%s\"," +
                MIN_API_VERSION +
                SENDER +
                TRACKING_DATA +
                TYPE +
                "\"text\":\"%s\"," +
                "\"auth_token\":\"%s\"}",
                userId, message, token)
        return body
    }

    fun send(url: String, body: String) {
        val httpClient = OkHttpClient()

        // json request body
        val body = body.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request: Request = Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build()
        val response = httpClient.newCall(request).execute()
    }

    private fun getToken(data: String?): String {
        data?.let {
            val keys = it.split(";")
            for (key in keys) {
                val pair = key.split("=")
                if (pair.contains("token")) {
                    return pair[1]
                }
            }

        }
        return ""
    }
}