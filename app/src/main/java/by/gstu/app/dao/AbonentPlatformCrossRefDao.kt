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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(crossRef: AbonentPlatformCrossRef) : Completable

    @Query("SELECT * FROM AbonentPlatformCrossRef WHERE abonentId=:abonentId")
    fun getCrossRefByAbonentId(abonentId: Long) : LiveData<AbonentPlatformCrossRef>

    @Query("SELECT * FROM AbonentPlatformCrossRef WHERE platformName=:platformName")
    fun getCrossRefByPlatformName(platformName: String) : LiveData<AbonentPlatformCrossRef>

    @Transaction
    @Query("SELECT * FROM abonent WHERE abonentId=:abonentId")
    fun getPlatformsOfAbonent(abonentId: Long) : LiveData<AbonentWithPlatforms>

    @Transaction
    @Query("SELECT * FROM platform WHERE platformName=:platformName")
    fun getAbonentsOfPlatform(platformName: String) : LiveData<PlatformWithAbonents>
}