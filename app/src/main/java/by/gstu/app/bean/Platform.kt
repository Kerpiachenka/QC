package by.gstu.app.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Platform(
        @PrimaryKey(autoGenerate = false) val platformName: String, // TODO: Don't forget to make changes in record!!!
        val isActive: Boolean,
        val data: String? = null,
        val instruction: String? = null
) : Serializable
