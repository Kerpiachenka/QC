package by.gstu.app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.AbonentWithPlatforms
import by.gstu.app.bean.PlatformWithAbonents
import by.gstu.app.call.*
import by.gstu.app.dao.AbonentPlatformCrossRefDao
import by.gstu.app.database.AppDatabase
import by.gstu.app.listener.BaseQueryResultListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AbonentPlatformCrossRefRepositoryImpl(private val context: Context)
    : AbonentPlatformCrossRefRepository {

    private var dao: AbonentPlatformCrossRefDao = AppDatabase.getInstance(context).abonentWithPlatformDao
    var listener: BaseQueryResultListener? = null

    override fun getPlatformsOfAbonent(abonentId: Long): LiveData<AbonentWithPlatforms> {
        return dao.getPlatformsOfAbonent(abonentId)
    }

    override fun getAbonentsOfPlatform(platformName: String): LiveData<PlatformWithAbonents> {
        return dao.getAbonentsOfPlatform(platformName)
    }

    override fun getCrossRefByAbonentId(abonentId: Long): LiveData<AbonentPlatformCrossRef> {
        return dao.getCrossRefByAbonentId(abonentId)
    }

    override fun getCrossRefByPlatformName(platformName: String): LiveData<AbonentPlatformCrossRef> {
        return dao.getCrossRefByPlatformName(platformName)
    }

    override fun insert(crossRef: AbonentPlatformCrossRef) {
        execute(InsertAbonentPlatformCallableAction(crossRef, dao, context))
    }

    override fun update(crossRef: AbonentPlatformCrossRef) {
        execute(UpdateAbonentPlatformCallableAction(crossRef, dao, context))
    }

    override fun delete(crossRef: AbonentPlatformCrossRef) {
        execute(DeleteAbonentPlatformCallableAction(crossRef, dao, context))
    }

    private fun execute(callableAction: AbonentPlatformConnectionCallableAction) {
        Observable.fromCallable(callableAction)
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