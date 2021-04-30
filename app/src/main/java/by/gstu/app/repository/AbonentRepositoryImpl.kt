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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AbonentRepositoryImpl(private val context: Context) : AbonentRepository {

    var dao: AbonentDao

    init {
        val db = Room.databaseBuilder(context,
                AppDatabase::class.java, "database").build()
        dao = db.abonentDao()
    }


    override fun getAll(): LiveData<List<Abonent>> {
        return dao.getAll()
    }

    override fun insert(abonent: Abonent) {
        execute(InsertAbonentCallableAction(abonent, dao, context))
    }

    override fun update(abonent: Abonent) {
        execute(UpdateAbonentCallableAction(abonent, dao, context))
    }

    override fun delete(abonent: Abonent) {
        execute(DeleteAbonentCallableAction(abonent, dao, context))
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
