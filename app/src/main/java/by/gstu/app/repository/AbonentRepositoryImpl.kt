package by.gstu.app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import by.gstu.app.bean.Abonent
import by.gstu.app.call.AbonentCallableAction
import by.gstu.app.call.DeleteAbonentCallableAction
import by.gstu.app.call.InsertAbonentCallableAction
import by.gstu.app.call.UpdateAbonentCallableAction
import by.gstu.app.dao.AbonentDao
import by.gstu.app.database.AppDatabase
import by.gstu.app.listener.BaseQueryResultListener
import by.gstu.app.listener.ManageAbonentListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AbonentRepositoryImpl(private val context: Context)
    : AbonentRepository {

    private var dao: AbonentDao
    var listener: BaseQueryResultListener? = null

    init {
        val db = Room.databaseBuilder(context,
                AppDatabase::class.java, "database").build()
        dao = db.abonentDao()
    }


    override fun getAll(): LiveData<List<Abonent>> {
        return dao.getAll()
    }

    override fun insert(obj: Abonent) {
        execute(InsertAbonentCallableAction(obj, dao, context))
    }

    override fun update(obj: Abonent) {
        execute(UpdateAbonentCallableAction(obj, dao, context))
    }

    override fun delete(obj: Abonent) {
        execute(DeleteAbonentCallableAction(obj, dao, context))
    }

    private fun execute(abonentCallableAction: AbonentCallableAction) {
        Observable.fromCallable(abonentCallableAction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    when (res) {
                        true -> listener?.onSuccess()
                        false -> listener?.onFailure("Something went wrong during query execution.")
                    }
                }
    }
}
