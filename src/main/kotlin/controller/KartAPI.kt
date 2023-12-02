package controller
import models.Lap
import models.Kart
import persistence.Serializer
import java.util.ArrayList

class KartAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType

    private var karts = ArrayList<Kart>()

    fun add(kart: Kart): Boolean {
        return karts.add(kart)
    }














}