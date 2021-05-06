package by.gstu.app.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Platform(@PrimaryKey(autoGenerate = true) val id : Long, val name: String)
