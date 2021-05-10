package by.gstu.app.bean

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlatformWithAbonents(
        @Embedded val platform: Platform,

        @Relation(
                parentColumn = "platformId",
                entityColumn = "abonentId",
                associateBy = Junction(AbonentPlatformCrossRef::class)
        )
        var abonents: List<Abonent>
)
