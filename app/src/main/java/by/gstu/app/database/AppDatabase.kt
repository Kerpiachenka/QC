package by.gstu.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.gstu.app.bean.Abonent
import by.gstu.app.dao.AbonentDao

@Database(entities = [Abonent::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun abonentDao(): AbonentDao
}