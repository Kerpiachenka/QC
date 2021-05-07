package by.gstu.app.repository

import androidx.lifecycle.LiveData
import by.gstu.app.bean.Platform

interface PlatformRepository : BaseRepository<Platform> {
    fun getCount(): LiveData<Int>
}