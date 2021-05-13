package by.gstu.app.sender

import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener

interface Sender {
    fun sendToPlatform(
            identifier: String,
            message: String,
            platform: Platform,
            listener: BaseQueryResultListener)
}