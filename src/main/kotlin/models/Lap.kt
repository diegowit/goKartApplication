package models

/**
 * Represents a lap with its properties and completion status.
 *
 * @property lapId Unique identifier for the lap.
 * @property driverName Name of the driver.
 * @property driverAge Age of the driver.
 * @property distance Distance covered in the lap.
 * @property time Time taken to complete the lap.
 * @property speed Speed achieved during the lap.
 * @property completedLap Description of lap completion status.
 * @property isLapCompleted Indicates if the lap is completed.
 */
data class Lap(
    var lapId: Int = 0,
    var driverName: String,
    var driverAge: Int,
    var distance: Double,
    var time: Double,
    var speed: Double,
    var completedLap: String,
    var isLapCompleted: Boolean = false
) {
    /**
     * Get a string representation of the lap, including completion status.
     *
     * @return The string representation of the lap.
     */
    override fun toString(): String {
        return if (isLapCompleted) {
            "$lapId: Name: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $completedLap:\" (Lap Was Complete)   "
        } else {
            "$lapId: Name: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $completedLap:\" (Lap Was not Complete)   "
        }
    }
}