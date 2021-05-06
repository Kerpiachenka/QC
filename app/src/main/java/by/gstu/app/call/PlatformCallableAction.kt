package by.gstu.app.call

import android.content.Context
import android.widget.Toast
import by.gstu.app.bean.Platform
import by.gstu.app.dao.PlatformDao
import io.reactivex.Completable
import java.util.concurrent.Callable

abstract class PlatformCallableAction(val platform: Platform,
                                      val platformDao: PlatformDao,
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

class InsertPlatformCallableAction(platform: Platform, platformDao: PlatformDao, context: Context)
    : PlatformCallableAction(platform, platformDao, context) {
    override fun call(): Boolean {
        executeCompletable(platformDao.insert(platform))
        return true
    }
}

class DeletePlatformCallableAction(platform: Platform, platformDao: PlatformDao, context: Context)
    : PlatformCallableAction(platform, platformDao, context) {
    override fun call(): Boolean {
        executeCompletable(platformDao.delete(platform))
        return true
    }
}

class UpdatePlatformCallableAction(platform: Platform, platformDao: PlatformDao, context: Context)
    : PlatformCallableAction(platform, platformDao, context) {
    override fun call(): Boolean {
        executeCompletable(platformDao.update(platform))
        return true
    }
}