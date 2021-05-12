package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentWithPlatforms
import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepository
import by.gstu.app.repository.AbonentPlatformCrossRefRepositoryImpl
import by.gstu.app.repository.AbonentRepository

class ManageAbonentViewModel : ViewModel(), BaseQueryResultListener {
    var repository: AbonentRepository? = null
    var listener: BaseQueryResultListener? = null
    var name: String? = null
    var age: String? = null
    var abonent: Abonent? = null

    fun saveChangesButtonClick(view: View) {
        if (name.isNullOrBlank() || age.isNullOrBlank()) {
            listener?.onFailure("Incorrect name or age")
            return
        }
        when(abonent) {
            null -> createNew()
            else -> updateExists()
        }
    }

    fun retrievePlatformsOfAbonent(
            abonent: Abonent, repository:
            AbonentPlatformCrossRefRepository
    ): LiveData<AbonentWithPlatforms>{
        return repository.getPlatformsOfAbonent(abonentId = abonent.abonentId)
    }

    private fun updateExists() {
        repository?.update(Abonent(abonent!!.abonentId, name!!, age!!.toInt()))
    }

    private fun createNew() {
        repository?.insert(Abonent(0, name!!, age!!.toInt()))
    }

    fun deleteButtonClick(view: View) {
        if (repository == null) {
            listener?.onFailure("Repository must be initialized.")
            return
        }
        repository!!.delete(abonent!!)
    }

    override fun onSuccess() {
        listener?.onSuccess()
    }

    override fun onFailure(message: String) {
        listener?.onFailure(message)
    }
}