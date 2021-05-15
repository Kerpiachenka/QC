package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.ManageAbonentListener
import by.gstu.app.repository.AbonentPlatformCrossRefRepository
import by.gstu.app.repository.PlatformRepository
import by.gstu.app.util.PlatformDataValidator

class ManagePlatformViewModel() : ViewModel(), BaseQueryResultListener {
    var repository: PlatformRepository? = null
    var crossRefRepository: AbonentPlatformCrossRefRepository? = null
    var dataValidator = PlatformDataValidator()

    var data: String? = null
    var name: String? = null
    var instruction: String? = null
    lateinit var platform: Platform

    var listener: BaseQueryResultListener? = null

    fun saveChangesButtonClick(view: View) {
        if (!dataValidator.isValid(data)) {
            onFailure("Incorrect data. Please follow instructions bellow.")
            return
        }
        when(platform.isActive) {
            true -> updateExists()
            else -> addToActiveGroup()
        }
    }

    private fun updateExists() {
        execute(Platform(platform.platformName,
            true, data, platform.instruction))
    }

    private fun addToActiveGroup() {
        execute(Platform(platform.platformName,
            true, data, platform.instruction))
    }

    fun deleteButtonClick(view: View) {
        execute(Platform(platform.platformName,
            false, null, platform.instruction))
        crossRefRepository?.deactivatePlatform(
                AbonentPlatformCrossRef(0, platform.platformName, ""))
    }

    private fun execute(platform: Platform) {
        if (repository == null) {
            onFailure("Repository must be initialized.")
            return
        }
        repository!!.update(platform)
    }

    override fun onSuccess() {
        listener?.onSuccess()
    }

    override fun onFailure(message: String) {
        listener?.onFailure(message)
    }
}