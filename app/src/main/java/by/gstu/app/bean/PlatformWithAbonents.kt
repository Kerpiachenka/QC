package by.gstu.app.bean

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlatformWithAbonents(
        @Embedded val platform: Platform,

        @Relation(
                parentColumn = "platformName",
                entityColumn = "abonentId",
                associateBy = Junction(AbonentPlatformCrossRef::class)
        ) val abonents: List<Abonent>
)
