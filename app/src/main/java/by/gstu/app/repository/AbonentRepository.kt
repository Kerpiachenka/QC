package by.gstu.app.repository

import androidx.lifecycle.LiveData
import by.gstu.app.bean.Abonent

interface AbonentRepository {
    fun getAll(): LiveData<List<Abonent>>
    fun insert(abonent: Abonent)
    fun update(abonent: Abonent)
    fun delete(abonent: Abonent)
}