package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.*
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.Platform
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentRepository
import by.gstu.app.repository.AbonentRepositoryImpl
import by.gstu.app.repository.PlatformRepository

class MainActivityViewModel : ViewModel() {
    var platformRepository: PlatformRepository? = null
    private lateinit var platformCount: LiveData<Int>
    var mainActivityListener: MainActivityListener? = null
    var abonentRepository: AbonentRepository? = null
    lateinit var abonentList: LiveData<List<Abonent>>

    fun updatePlatformsCount() {
        platformCount = platformRepository?.getCount()!!
    }

    fun initializePlatforms(data: List<Platform>) {
        for (item in data) {
            platformRepository?.insert(item)
        }
    }

    fun openManageAbonentActivity(view: View) {
        mainActivityListener?.onOpenManageAbonentActivity()
    }

    fun getPlatformsCount(): LiveData<Int> {
        return platformCount
    }

    fun openManagePlatformActivity(view: View) {
        mainActivityListener?.onOpenManagePlatformActivity()
    }

    fun openSettingsActivity(view: View) {
        mainActivityListener?.onOpenSettingsActivity()
    }

    fun updateList() {
        abonentList = abonentRepository?.getAll()!!
    }

    fun getAllAbonent() : LiveData<List<Abonent>> {
        return abonentList
    }
}