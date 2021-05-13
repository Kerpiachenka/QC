package by.gstu.app.bean

import androidx.room.Entity

@Entity(primaryKeys = ["abonentId", "platformName"])
data class AbonentPlatformCrossRef(
        var abonentId : Long,
        var platformName: String,
        var userIdentifier: String
)
