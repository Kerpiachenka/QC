package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import by.gstu.app.listener.ManageAbonentListener

class ManageAbonentViewModel : ViewModel() {
    var name: String? = null
    var age: String? = null

    var manageAbonentListener: ManageAbonentListener? = null

    fun saveChangesButtonClick(view: View) {
        manageAbonentListener?.onStarted()

        manageAbonentListener?.onSuccess()
    }

    fun deleteContactButtonClick(view: View) {

    }
}