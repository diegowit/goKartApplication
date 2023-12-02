package utils
import models.Lap
import models.Kart
object Utilities {
    @JvmStatic
    fun formatListString(kartsToFormat: List<Kart>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->  "$kart" }

    @JvmStatic
    fun formatSetString(labsToFormat: Set<Lap>): String =
        labsToFormat
            .joinToString(separator = "\n") { lab ->  "\t$lab" }

}
