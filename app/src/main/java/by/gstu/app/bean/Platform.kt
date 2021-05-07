package by.gstu.app.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Platform(
        @PrimaryKey(autoGenerate = true) val id : Long,
        val name: String,
        val isActive: Boolean,
        val data: String?
) : Serializable
