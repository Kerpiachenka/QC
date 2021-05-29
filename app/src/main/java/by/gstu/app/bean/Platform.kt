package by.gstu.app.bean

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Platform(
        @PrimaryKey(autoGenerate = false) val platformName: String,
        val isActive: Boolean,
        val data: String? = null,
        val instruction: String? = null,
) : Serializable
