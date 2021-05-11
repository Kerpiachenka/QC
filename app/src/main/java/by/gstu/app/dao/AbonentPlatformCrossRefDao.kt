package by.gstu.app.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.AbonentWithPlatforms
import by.gstu.app.bean.PlatformWithAbonents
import io.reactivex.Completable

@Dao
interface AbonentPlatformCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(crossRef: AbonentPlatformCrossRef) : Completable

    @Delete
    fun delete(crossRef: AbonentPlatformCrossRef) : Completable

    @Update
    fun update(crossRef: AbonentPlatformCrossRef) : Completable

    @Transaction
    @Query("SELECT * FROM abonent WHERE abonentId=:abonentId")
    fun getPlatformsOfAbonent(abonentId: Long) : LiveData<AbonentWithPlatforms>

    @Transaction
    @Query("SELECT * FROM platform WHERE platformName=:platformName")
    fun getAbonentsOfPlatform(platformName: String) : LiveData<PlatformWithAbonents>
}