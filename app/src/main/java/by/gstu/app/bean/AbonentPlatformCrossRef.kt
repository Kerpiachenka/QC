package by.gstu.app.bean

import androidx.room.Entity

@Entity(primaryKeys = ["abonentId", "platformId"])
data class AbonentPlatformCrossRef(
        val abonentId : Long,
        val platformId: Long,
        val userIdentifier: String
)
