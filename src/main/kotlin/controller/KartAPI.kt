package controller
import models.Lap
import models.Kart
import persistence.Serializer
import utils.Utilities.isValidListIndex
import java.util.ArrayList

class KartAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var karts: MutableList<Kart> = mutableListOf()


    private var lastId = 0
    private fun getId() = lastId++


    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, karts)
    }

    fun findKart(index: Int): Kart? {
        return if (isValidIndex(index)) {
            karts[index]
        } else null
    }


    fun add(kart: Kart): Boolean {
        kart.KartId = getId()
        return karts.add(kart)
    }

    fun deleteKart(indexToDelete: Int): Kart? {
        return if (isValidListIndex(indexToDelete, karts)) {
            karts.removeAt(indexToDelete)
        } else null
    }

}









