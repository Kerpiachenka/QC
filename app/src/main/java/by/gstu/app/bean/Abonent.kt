package by.gstu.app.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Abonent(@PrimaryKey(autoGenerate = true) val id : Long, val name: String, val age: Int)
    : Serializable
