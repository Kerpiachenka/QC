package by.gstu.app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import by.gstu.app.bean.Platform
import by.gstu.app.call.DeletePlatformCallableAction
import by.gstu.app.call.InsertPlatformCallableAction
import by.gstu.app.call.PlatformCallableAction
import by.gstu.app.call.UpdatePlatformCallableAction
import by.gstu.app.dao.PlatformDao
import by.gstu.app.database.AppDatabase
import by.gstu.app.listener.BaseQueryResultListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PlatformRepositoryImpl(private val context: Context)
    : PlatformRepository {

    private var dao: PlatformDao
    var listener: BaseQueryResultListener? = null

    init {
        val db = Room.databaseBuilder(context,
                AppDatabase::class.java, "database").build()
        dao = db.platformDao()
    }

    override fun getCount(): LiveData<Int> {
        return dao.getCount()
    }

    override fun getAll(): LiveData<List<Platform>> {
        return dao.getAll()
    }

    override fun insert(obj: Platform) {
        execute(InsertPlatformCallableAction(obj, dao, context))
    }

    override fun update(obj: Platform) {
        execute(UpdatePlatformCallableAction(obj, dao, context))
    }

    override fun delete(obj: Platform) {
        execute(DeletePlatformCallableAction(obj, dao, context))
    }

    private fun execute(platformCallableAction: PlatformCallableAction) {
        Observable.fromCallable(platformCallableAction)
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