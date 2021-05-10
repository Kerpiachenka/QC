package by.gstu.app.call

import android.content.Context
import android.widget.Toast
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.dao.AbonentPlatformCrossRefDao
import io.reactivex.Completable
import java.util.concurrent.Callable

abstract class AbonentPlatformConnectionCallableAction (
        val data: AbonentPlatformCrossRef,
        val dao: AbonentPlatformCrossRefDao,
        private val context: Context) : Callable<Boolean> {

    protected fun executeCompletable(completable: Completable) {
        completable.subscribe(
                { showToast("Successfully competed") },
                { ex -> showToast(ex.message.toString())
                })
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

class InsertAbonentPlatformCallableAction(data: AbonentPlatformCrossRef,
                                          dao: AbonentPlatformCrossRefDao,
                                          context: Context)
    : AbonentPlatformConnectionCallableAction(data, dao, context) {
    override fun call(): Boolean {
        executeCompletable(dao.insert(data))
        return true
    }
}

class DeleteAbonentPlatformCallableAction(data: AbonentPlatformCrossRef,
                                          dao: AbonentPlatformCrossRefDao,
                                          context: Context)
    : AbonentPlatformConnectionCallableAction(data, dao, context) {
    override fun call(): Boolean {
        executeCompletable(dao.delete(data))
        return true
    }
}

class UpdateAbonentPlatformCallableAction(data: AbonentPlatformCrossRef,
                                          dao: AbonentPlatformCrossRefDao,
                                          context: Context)
    : AbonentPlatformConnectionCallableAction(data, dao, context) {
    override fun call(): Boolean {
        executeCompletable(dao.update(data))
        return true
    }
}