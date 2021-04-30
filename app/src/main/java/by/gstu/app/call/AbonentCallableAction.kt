package by.gstu.app.call

import android.app.Application
import android.content.Context
import android.widget.Toast
import by.gstu.app.bean.Abonent
import by.gstu.app.dao.AbonentDao
import io.reactivex.Completable
import java.util.concurrent.Callable

abstract class AbonentCallableAction(val abonent: Abonent,
                                     val abonentDao: AbonentDao,
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

class InsertAbonentCallableAction(abonent: Abonent, abonentDao: AbonentDao, context: Context)
    : AbonentCallableAction(abonent, abonentDao, context) {
    override fun call(): Boolean {
        executeCompletable(abonentDao.insert(abonent))
        return true
    }
}

class DeleteAbonentCallableAction(abonent: Abonent, abonentDao: AbonentDao, context: Context)
    : AbonentCallableAction(abonent, abonentDao, context) {
    override fun call(): Boolean {
        executeCompletable(abonentDao.delete(abonent))
        return true
    }
}

class UpdateAbonentCallableAction(abonent: Abonent, abonentDao: AbonentDao, context: Context)
    : AbonentCallableAction(abonent, abonentDao, context) {
    override fun call(): Boolean {
        executeCompletable(abonentDao.update(abonent))
        return true
    }
}