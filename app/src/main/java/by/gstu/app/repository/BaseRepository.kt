package by.gstu.app.repository

import androidx.lifecycle.LiveData

interface BaseRepository<T> {
    fun getAll(): LiveData<List<T>>
    fun insert(obj: T)
    fun update(obj: T)
    fun delete(obj: T)
}