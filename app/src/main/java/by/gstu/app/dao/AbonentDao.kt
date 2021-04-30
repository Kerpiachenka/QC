package by.gstu.app.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.gstu.app.bean.Abonent
import io.reactivex.Completable

@Dao
interface AbonentDao {
    @Query("SELECT * FROM abonent")
    fun getAll(): LiveData<List<Abonent>>

    @Insert
    fun insert(abonent: Abonent) : Completable

    @Update
    fun update(abonent: Abonent) : Completable

    @Delete
    fun delete(abonent: Abonent): Completable
}