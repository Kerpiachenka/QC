package by.gstu.app.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.gstu.app.bean.Platform
import io.reactivex.Completable

@Dao
interface PlatformDao {
    @Query("SELECT * FROM platform")
    fun getAll(): LiveData<List<Platform>>

    @Query("SELECT * FROM platform WHERE id=:id")
    fun getById(id: Int): LiveData<Platform>

    @Query("SELECT COUNT(*) FROM platform")
    fun getCount(): LiveData<Int>

    @Insert
    fun insert(platform: Platform) : Completable

    @Update
    fun update(platform: Platform) : Completable

    @Delete
    fun delete(platform: Platform): Completable
}