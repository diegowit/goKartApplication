package utils
import models.Lap
import models.Kart
object Utilities {
    @JvmStatic
    fun formatListString(kartsToFormat: List<Kart>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->  "$kart" }

    @JvmStatic
    fun formatSetString(lapsToFormat: Set<Lap>): String =
        lapsToFormat
            .joinToString(separator = "\n") { lap ->  "\t$lap" }

}
