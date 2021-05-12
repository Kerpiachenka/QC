package by.gstu.app.bean

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import java.io.Serializable

data class AbonentWithPlatforms(
        @Embedded val abonent: Abonent,

        @Relation(
                parentColumn = "abonentId",
                entityColumn = "platformName",
                associateBy = Junction(AbonentPlatformCrossRef::class)
        ) val platforms: List<Platform>
) : Serializable
