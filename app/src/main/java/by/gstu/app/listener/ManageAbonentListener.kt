package by.gstu.app.listener

interface ManageAbonentListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}