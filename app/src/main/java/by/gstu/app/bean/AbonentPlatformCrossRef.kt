package by.gstu.app.bean

import androidx.room.Entity

@Entity(primaryKeys = ["abonentId", "platformName"])
data class AbonentPlatformCrossRef(
        val abonentId : Long,
        val platformName: String,
        val userIdentifier: String
)
