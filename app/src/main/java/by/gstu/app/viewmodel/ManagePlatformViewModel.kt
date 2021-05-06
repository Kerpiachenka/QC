package by.gstu.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Platform
import by.gstu.app.repository.PlatformRepository

class ManagePlatformViewModel : ViewModel() {
    var platformRepository: PlatformRepository? = null
    lateinit var platformList: LiveData<List<Platform>>

    fun updateList() {
        platformList = platformRepository?.getAll()!!
    }

    fun getAllPlatform() : LiveData<List<Platform>> {
        return platformList
    }
}