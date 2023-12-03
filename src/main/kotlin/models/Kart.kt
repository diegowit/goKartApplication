package models
import utils.Utilities
/**
 * Represents a Go Kart with its properties and lap-related functionalities.
 *
 * @property KartId Unique identifier for the kart.
 * @property model Model name of the kart.
 * @property weight Weight of the kart.
 * @property maxSpeed Maximum speed of the kart.
 * @property enginePower Engine power of the kart.
 * @property isElectric Indicates if the kart is electric.
 * @property laps Set of laps associated with the kart.
 */
data class Kart(
    var KartId: Int = 0,
    var model: String,
    var weight: Int,
    var maxSpeed: Double,
    var enginePower: Int,
    var isElectric: Boolean = false,
    var laps: MutableSet<Lap> = mutableSetOf()
) {
    private var lastLap = 0

    /**
     * Get the next available lapId.
     *
     * @return The next available lapId.
     */
    private fun getLapId() = lastLap++

    /**
     * Add a lap to the kart.
     *
     * @param lap The lap to be added.
     * @return True if lap addition is successful, false otherwise.
     */
    fun addLap(lap: Lap): Boolean {
        lap.lapId = getLapId()
        return laps.add(lap)
    }

    /**
     * Delete a lap with a specific lapId from the kart.
     *
     * @param id The lapId of the lap to be deleted.
     * @return True if lap deletion is successful, false otherwise.
     */
    fun deleteLap(id: Int): Boolean {
        return laps.removeIf { lap -> lap.lapId == id }
    }

    /**
     * Get the total number of laps associated with the kart.
     *
     * @return The total number of laps.
     */
    fun numberOfLaps() = laps.size

    /**
     * Find a lap by lapId.
     *
     * @param id The lapId to search for.
     * @return The Lap object if found, null otherwise.
     */
    fun findOne(id: Int): Lap? {
        return laps.find { lap -> lap.lapId == id }
    }

    /**
     * Update lap information by lapId.
     *
     * @param id The lapId of the lap to be updated.
     * @param newLap The updated lap information.
     * @return True if lap update is successful, false otherwise.
     */
    fun updateLap(id: Int, newLap: Lap): Boolean {
        val foundLap = findOne(id)

        if (foundLap != null) {
            foundLap.driverName = newLap.driverName
            foundLap.driverAge = newLap.driverAge
            foundLap.distance = newLap.distance
            foundLap.time = newLap.time
            foundLap.speed = newLap.speed
            foundLap.completedLap = newLap.completedLap
            foundLap.isLapCompleted = newLap.isLapCompleted
            return true
        }

        // if the lap was not found, return false, indicating that the update was not successful
        return false
    }

    /**
     * Check if all laps associated with the kart are completed.
     *
     * @return True if all laps are completed, false otherwise.
     */
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

    /**
     * Get a formatted string representation of all laps associated with the kart.
     *
     * @return The formatted string representing laps.
     */
    fun listLaps() =
        if (laps.isEmpty()) "\tNO LAPS ADDED"
        else Utilities.formatSetString(laps)

    /**
     * Get a string representation of the kart.
     *
     * @return The string representation of the kart.
     */
    override fun toString(): String {
        val electric = if (isElectric) 'Y' else 'N'
        return "$KartId: Model: $model, weight($weight), Max Speed($maxSpeed), enginePower($enginePower), Electric($electric) \n${listLaps()}"
    }
}