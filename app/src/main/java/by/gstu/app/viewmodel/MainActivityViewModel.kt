package by.gstu.app.viewmodel

import android.view.View
import androidx.lifecycle.*
import by.gstu.app.bean.Abonent
import by.gstu.app.listener.MainActivityListener
import by.gstu.app.repository.AbonentRepository
import by.gstu.app.repository.AbonentRepositoryImpl

class MainActivityViewModel : ViewModel() {

    var mainActivityListener: MainActivityListener? = null
    var abonentRepository: AbonentRepository? = null
    var lifecycleOwner: LifecycleOwner? = null
    // TODO: inject recyclerView: AbonentRecyclerView component

    fun openManageAbonentActivity(view: View) {
        mainActivityListener?.onOpenManageAbonentActivity()
    }

    fun loadAbonentList() {
        lifecycleOwner?.let { abonentRepository?.getAll()?.observe(it, {
                    // TODO: add data to recyclerview component
                })
        }
    }
}