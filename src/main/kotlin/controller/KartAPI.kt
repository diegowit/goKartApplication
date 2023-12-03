package controller
import models.Lap
import models.Kart
import persistence.Serializer
import utils.Utilities
import utils.Utilities.formatListString
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

    fun updateKart(indexToUpdate: Int, kart: Kart?): Boolean {
        //find the note object by the index number
        val foundKart = findKart(indexToUpdate)

        //if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundKart != null) && (kart != null)) {
            foundKart.KartId = kart.KartId
            foundKart.model = kart.model
            foundKart.weight = kart.weight
            foundKart.maxSpeed = kart.maxSpeed
            foundKart.enginePower = kart.enginePower
            foundKart.isElectric = kart.isElectric
            foundKart.isFuel = kart.isFuel
            foundKart.laps = kart.laps
            return true
        }
        //if the note was not found, return false, indicating that the update was not successful
        return false
    }


    fun isKartElectric(id: Int): Boolean {
        val foundKart = findKart(id)

        if (foundKart != null && !foundKart.isElectric && foundKart.checkLapCompletionStatus()) {
            foundKart.isElectric = true
            return true
        }

        return false
    }




    fun listAllKart(): String =
        if (karts.isEmpty()) "No Karts stored" // Check for empty notes list.
        else formatListString(karts) // Format the list of all notes.


    fun listElectricKarts() =
        if (numberOfElectricKarts() == 0) "No Electric Karts stored"
        else Utilities.formatListString(karts.filter { kart -> kart.isElectric })

    fun listFuelKarts()=
        if (numberOfFuelKarts() == 0) "No Fuel Karts stored"
        else Utilities.formatListString(karts.filter { kart -> !kart.isElectric })



    fun numberOfKarts(): Int = karts.size
    fun numberOfElectricKarts(): Int = karts.count { kart: Kart -> kart.isElectric}
    fun numberOfFuelKarts(): Int = karts.count { kart: Kart -> !kart.isElectric}




    @Throws(Exception::class)
    fun load() {
        karts = serializer.read() as ArrayList<Kart>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(karts)
    }

    private fun formatListString(kartsToFormat : List<Kart>) : String =
        kartsToFormat
            .joinToString (separator = "\n") { kart ->
                karts.indexOf(kart).toString() + ": " + kart.toString() }

}















