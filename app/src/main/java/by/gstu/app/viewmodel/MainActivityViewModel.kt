package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.*
import by.gstu.app.adapter.AbonentRecyclerViewAdapter
import by.gstu.app.bean.Abonent
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentRepository
import by.gstu.app.repository.AbonentRepositoryImpl

class MainActivityViewModel : ViewModel() {

    var mainActivityListener: MainActivityListener? = null
    var abonentRepository: AbonentRepository? = null
    lateinit var abonentList: LiveData<List<Abonent>>

    fun openManageAbonentActivity(view: View) {
        mainActivityListener?.onOpenManageAbonentActivity()
    }

    fun updateList() {
        abonentList = abonentRepository?.getAll()!!
    }

    fun getAllAbonent() : LiveData<List<Abonent>> {
        return abonentList
    }
}