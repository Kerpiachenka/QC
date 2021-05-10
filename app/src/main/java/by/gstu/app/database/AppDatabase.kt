package by.gstu.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.Platform
import by.gstu.app.dao.AbonentDao
import by.gstu.app.dao.AbonentPlatformCrossRefDao
import by.gstu.app.dao.PlatformDao

@Database(
        entities = [
            Abonent::class,
            Platform::class,
            AbonentPlatformCrossRef::class
        ],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val abonentDao: AbonentDao
    abstract val platformDao: PlatformDao
    abstract val abonentWithPlatformDao: AbonentPlatformCrossRefDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE?: Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}