package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.AbonentWithPlatforms
import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepository
import by.gstu.app.repository.AbonentPlatformCrossRefRepositoryImpl
import by.gstu.app.repository.AbonentRepository
import by.gstu.app.repository.PlatformRepository
import by.gstu.app.util.AbonentAgeValidator
import by.gstu.app.util.AbonentNameValidator

class ManageAbonentViewModel : ViewModel(), BaseQueryResultListener {
    var repository: AbonentRepository? = null
    var listener: BaseQueryResultListener? = null
    var name: String? = null
    var age: String? = null
    var abonent: Abonent? = null
    private val ageValidator = AbonentAgeValidator()
    private val nameValidator = AbonentNameValidator()

    fun saveChangesButtonClick(view: View) {
        if (!isAgeFieldValid() || !isNameFieldValid()) {
            listener?.onFailure("Incorrect name or age")
            return
        }
        when(abonent) {
            null -> createNew()
            else -> updateExists()
        }
    }

    private fun isAgeFieldValid(): Boolean {
        if (age == null || age.toString().isEmpty()) {
            return ageValidator.isValid(null)
        }
        return ageValidator.isValid(age.toString().toInt())
    }

    private fun isNameFieldValid(): Boolean {
        return nameValidator.isValid(name.toString())
    }

    fun getCrossRef(id: Long, platformName: String,
                    repository: AbonentPlatformCrossRefRepository
    ): LiveData<AbonentPlatformCrossRef> {
        return repository.getCrossRefByAbonentAndPlatform(id, platformName)
    }

    fun retrieveAllAvailablePlatforms(repository: PlatformRepository): LiveData<List<Platform>>{
        return repository.getAll()
    }

    fun addCrossRef(crossRef: AbonentPlatformCrossRef, repository: AbonentPlatformCrossRefRepository) {
        repository.insert(crossRef)
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