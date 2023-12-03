package models
import utils.Utilities
data class Kart(

    var KartId: Int = 0,
    var model: String ="Birel",
    var weight: Int,
    var maxSpeed: Double,
    var enginePower: Int,
    var isElectric: Boolean,
    var isFuel: Boolean,
    var laps: MutableSet<Lap> = mutableSetOf()

) {
    private var lastLap = 0
    private fun getLapId() = lastLap++

    fun addLap(lap: Lap): Boolean {
        lap.lapId = getLapId()
        return laps.add(lap)
    }

    fun deleteLap(id: Int): Boolean {
        return laps.removeIf { lap -> lap.lapId == id }
    }
  fun numberOfLaps() = laps.size

    fun findOne(id: Int): Lap?{
        return laps.find { lap -> lap.lapId == id  }
    }
    fun updateLap(id: Int, newLap: Lap): Boolean {
        val foundLap = findOne(id)

        if (foundLap != null) {
            foundLap.driverName = newLap.driverName
            foundLap.driverAge = newLap.driverAge
            foundLap.distance = newLap.distance
            foundLap.time = newLap.time
            foundLap.speed = newLap.speed
            foundLap.completedLab = newLap.completedLab
            foundLap.isLapCompleted = newLap.isLapCompleted
            return true
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false
    }

    fun checkLapCompletionStatus(): Boolean {
        if (laps.isNotEmpty()) {
            for (lap in laps) {
                if (!lap.isLapCompleted) {
                    return false
                }
            }
        }
        return true
    }

fun listLaps() =
    if (laps.isEmpty())  "\tNO LAPS ADDED"
    else  Utilities.formatSetString(laps)

    override fun toString(): String {
        val electric = if (isElectric) 'Y' else 'N'
        return "$KartId: Model: $model, weight($weight), Max Speed($maxSpeed), enginePower($enginePower), Electric($isElectric) \n${listLaps()}"
    }






}







