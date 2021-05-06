package by.gstu.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.Platform
import by.gstu.app.dao.AbonentDao
import by.gstu.app.dao.PlatformDao

@Database(entities = [Abonent::class, Platform::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun abonentDao(): AbonentDao
    abstract fun platformDao(): PlatformDao
}