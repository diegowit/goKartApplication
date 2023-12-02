package utils
import models.Lap
import models.Kart
import controller.KartAPI
object Utilities {
    @JvmStatic
    fun formatListString(kartsToFormat: List<Kart>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->  "$kart" }

    @JvmStatic
    fun formatSetString(lapsToFormat: Set<Lap>): String =
        lapsToFormat
            .joinToString(separator = "\n") { lap ->  "\t$lap" }

    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }


    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }

}

