package by.gstu.app.repository

import androidx.lifecycle.LiveData
import by.gstu.app.bean.AbonentPlatformCrossRef
import by.gstu.app.bean.AbonentWithPlatforms
import by.gstu.app.bean.PlatformWithAbonents

interface AbonentPlatformCrossRefRepository{
    fun getPlatformsOfAbonent(abonentId: Long) : LiveData<AbonentWithPlatforms>
    fun getAbonentsOfPlatform(platformName: String) : LiveData<PlatformWithAbonents>
    fun insert(crossRef: AbonentPlatformCrossRef)
    fun delete(crossRef: AbonentPlatformCrossRef)
    fun update(crossRef: AbonentPlatformCrossRef)
}