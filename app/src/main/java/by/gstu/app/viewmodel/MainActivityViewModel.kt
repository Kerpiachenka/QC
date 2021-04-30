package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import by.gstu.app.listener.MainActivityListener

class MainActivityViewModel : ViewModel() {

    var mainActivityListener: MainActivityListener? = null

    fun openManageAbonentActivity(view: View) {
        mainActivityListener?.onOpenManageAbonentActivity()
    }
}