package controller
import models.Kart
import persistence.Serializer
import utils.Utilities
import utils.Utilities.isValidListIndex
import java.util.ArrayList

class KartAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var karts: MutableList<Kart> = mutableListOf()

    private var lastId = 0
    private fun getId() = lastId++

    // Check if the index is a valid index for the karts list
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, karts)
    }

    // Find a kart by its index in the karts list
    fun findKart(index: Int): Kart? {
        return if (isValidIndex(index)) {
            karts[index]
        } else null
    }

    // Add a new kart to the karts list
    fun add(kart: Kart): Boolean {
        kart.KartId = getId()
        return karts.add(kart)
    }

    // Delete a kart at a specific index from the karts list
    fun deleteKart(indexToDelete: Int): Kart? {
        return if (isValidListIndex(indexToDelete, karts)) {
            karts.removeAt(indexToDelete)
        } else null
    }

    // Update kart information at a specific index in the karts list
    fun updateKart(indexToUpdate: Int, kart: Kart?): Boolean {
        // find the note object by the index number
        val foundKart = findKart(indexToUpdate)

        // if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundKart != null) && (kart != null)) {
            foundKart.KartId = kart.KartId
            foundKart.model = kart.model
            foundKart.weight = kart.weight
            foundKart.maxSpeed = kart.maxSpeed
            foundKart.enginePower = kart.enginePower
            foundKart.isElectric = kart.isElectric
            foundKart.laps = kart.laps
            return true
        }
        // if the note was not found, return false, indicating that the update was not successful
        return false
    }

    // Check if a kart is electric and update it if lap completion status allows
    fun isKartElectric(id: Int): Boolean {
        val foundKart = findKart(id)

        if (foundKart != null && !foundKart.isElectric && foundKart.checkLapCompletionStatus()) {
            foundKart.isElectric = true
            return true
        }

        return false
    }

    // List all karts with their details
    fun listAllKart(): String =
        if (karts.isEmpty()) "No Karts stored" // Check for empty notes list.
        else formatListString(karts) // Format the list of all notes.

    // List all electric karts
    fun listElectricKarts() =
        if (numberOfElectricKarts() == 0) "No Electric Karts stored"
        else Utilities.formatListString(karts.filter { kart -> kart.isElectric })

    // List all fuel-powered karts
    fun listFuelKarts() =
        if (numberOfFuelKarts() == 0) "No Fuel Karts stored"
        else Utilities.formatListString(karts.filter { kart -> !kart.isElectric })

    // Get the total number of karts
    fun numberOfKarts(): Int = karts.size

    // Get the number of electric karts
    fun numberOfElectricKarts(): Int = karts.count { kart: Kart -> kart.isElectric }

    // Get the number of fuel-powered karts
    fun numberOfFuelKarts(): Int = karts.count { kart: Kart -> !kart.isElectric }

    // Search for karts by model name
    fun searchByModel(searchString: String) =
        formatListString(karts.filter { kart -> kart.model.contains(searchString, ignoreCase = true) })

    // Search for karts by weight
    fun searchByWeight(searchInt: Int) =
        formatListString(karts.filter { kart -> kart.weight == searchInt })

    // Search for karts by engine power
    fun searchByEP(searchInt: Int) =
        formatListString(karts.filter { kart -> kart.enginePower == searchInt })

    // Search for laps by driver name
    fun searchLapByDriverName(searchString: String): String {
        return if (numberOfElectricKarts() == 0) "No Karts stored"
        else {
            var listOfKart = ""
            for (kart in karts) {
                for (lap in kart.laps) {
                    if (lap.driverName.contains(searchString, ignoreCase = true)) {
                        listOfKart += "${kart.KartId}: ${kart.maxSpeed} \n\t${lap}\n"
                    }
                }
            }
            if (listOfKart == "") "No Laps found for: $searchString"
            else listOfKart
        }
    }

    // Search for laps that are not completed
    fun SearchLapsNotCompleted(searchDetails: String): String =
        if (numberOfKarts() == 0) "No Karts stored"
        else {

            var listOfLapsNotCompleted = ""
            for (kart in karts) {
                for (lap in kart.laps) {
                    if (!lap.isLapCompleted) {
                        listOfLapsNotCompleted += kart.model + ":" + lap.completedLap + "\n"
                    }
                }
            }
            listOfLapsNotCompleted
        }

    // Get the total number of laps that are not completed
    fun numberOfNotCompletedLaps(): Int {
        var numberOfNotCompletedLaps = 0
        for (kart in karts) {
            for (lap in kart.laps) {
                if (!lap.isLapCompleted) {
                    numberOfNotCompletedLaps++
                }
            }
        }
        return numberOfNotCompletedLaps
    }

    @Throws(Exception::class)
    fun load() {
        karts = serializer.read() as ArrayList<Kart>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(karts)
    }

    private fun formatListString(kartsToFormat: List<Kart>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->
                karts.indexOf(kart).toString() + ": " + kart.toString()
            }
}
