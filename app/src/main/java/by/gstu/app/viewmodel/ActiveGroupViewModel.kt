package by.gstu.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.gstu.app.bean.Platform
import by.gstu.app.repository.PlatformRepository

class ActiveGroupViewModel : ViewModel() {
    var platformRepository: PlatformRepository? = null
    private lateinit var platformList: LiveData<List<Platform>>
    private lateinit var platformCount: LiveData<Int>

    fun updatePlatformsCount() {
        platformCount = platformRepository?.getCount()!!
    }

    fun initializePlatforms(data: List<Platform>) {
        for (item in data) {
            platformRepository?.insert(item)
        }
    }

    fun updateList() {
        platformList = platformRepository?.getAll()!!
    }

    fun getAllPlatform() : LiveData<List<Platform>> {
        return platformList
    }

    fun getPlatformsCount(): LiveData<Int> {
        return platformCount
    }
}