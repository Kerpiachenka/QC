package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Abonent
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentRepositoryImpl

class ManageAbonentViewModel : ViewModel() {
    var repository: AbonentRepositoryImpl? = null

    var name: String? = null
    var age: String? = null

    var manageAbonentListener: ManageAbonentListener? = null

    fun saveChangesButtonClick(view: View) {
        manageAbonentListener?.onStarted()
        if (name.isNullOrBlank() || age.isNullOrBlank()) {
            manageAbonentListener?.onFailure("Incorrect name or age")
            return
        }
        repository?.insert(Abonent(0, name!!, age!!.toInt()))
        manageAbonentListener?.onSuccess()
    }

    fun deleteContactButtonClick(view: View) {
        manageAbonentListener?.onStarted()

        manageAbonentListener?.onSuccess()
    }
}