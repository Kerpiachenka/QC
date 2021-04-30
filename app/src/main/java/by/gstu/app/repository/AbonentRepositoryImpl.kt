package by.gstu.app.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import by.gstu.app.bean.Abonent
import by.gstu.app.call.AbonentCallableAction
import by.gstu.app.call.DeleteAbonentCallableAction
import by.gstu.app.call.InsertAbonentCallableAction
import by.gstu.app.call.UpdateAbonentCallableAction
import by.gstu.app.dao.AbonentDao
import by.gstu.app.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

sealed class AbonentRepositoryImpl(app: Application) : AbonentRepository {

    var dao: AbonentDao
    var application: Application

    init {
        val db = Room.databaseBuilder(app,
                AppDatabase::class.java, "database").build()
        application = app
        dao = db.abonentDao()
    }


    override fun getAll(): LiveData<List<Abonent>> {
        return dao.getAll()
    }

    override fun insert(abonent: Abonent) {
        execute(InsertAbonentCallableAction(abonent, dao, application))
    }

    override fun update(abonent: Abonent) {
        execute(UpdateAbonentCallableAction(abonent, dao, application))
    }

    override fun delete(abonent: Abonent) {
        execute(DeleteAbonentCallableAction(abonent, dao, application))
    }

    private fun execute(abonentCallableAction: AbonentCallableAction) {
        Observable.fromCallable(abonentCallableAction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*.subscribe { obj ->
                    when (obj) {
                        true -> startActivity(Intent(applicationContext, MainActivity::class.java))
                        false -> showToast("Error")
                    }
                }*/
    }
}
