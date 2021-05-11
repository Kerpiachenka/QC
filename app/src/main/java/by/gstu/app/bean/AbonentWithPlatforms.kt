package by.gstu.app.bean

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AbonentWithPlatforms(
        @Embedded val abonent: Abonent,

        @Relation(
                parentColumn = "abonentId",
                entityColumn = "platformName",
                associateBy = Junction(AbonentPlatformCrossRef::class)
        ) val platforms: List<Platform>
)
