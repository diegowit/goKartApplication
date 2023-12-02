package controller
import models.Lap
import models.Kart
import persistence.Serializer
import java.util.ArrayList

class KartAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var karts: MutableList<Kart> = mutableListOf()


    private var lastId = 0
    private fun getId() = lastId++


    fun add(kart: Kart): Boolean {
        kart.KartId = getId()
        return karts.add(kart)
    }


















}