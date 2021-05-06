package by.gstu.app.listener

interface BaseQueryResultListener {
    fun onSuccess()
    fun onFailure(message: String)
}