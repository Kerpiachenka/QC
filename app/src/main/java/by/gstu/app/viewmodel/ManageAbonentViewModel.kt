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
    var abonent: Abonent? = null

    var manageAbonentListener: ManageAbonentListener? = null

    fun saveChangesButtonClick(view: View) {
        if (name.isNullOrBlank() || age.isNullOrBlank()) {
            manageAbonentListener?.onFailure("Incorrect name or age")
            return
        }
        when(abonent) {
            null -> createNew()
            else -> updateExists()
        }

        //manageAbonentListener?.onSuccess()
    }

    private fun updateExists() {
        repository?.update(Abonent(abonent!!.id, name!!, age!!.toInt()))
    }

    private fun createNew() {
        repository?.insert(Abonent(0, name!!, age!!.toInt()))
    }

    fun deleteContactButtonClick(view: View) {
        if (repository == null) {
            manageAbonentListener?.onFailure("Repository must be initialized.")
            return
        }
        repository!!.delete(abonent!!)
        //manageAbonentListener?.onSuccess()
    }
}