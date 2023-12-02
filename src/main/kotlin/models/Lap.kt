package models

data class Lap (
    var lapId: Int = 0,
    var driverName: String,
    var driverAge: Int,
    var distance: Double,
    val time: Double,
    val speed: Double,
    val isLapCompleted: Boolean = false

        )

{
    override fun toString(): String {

        if (isLapCompleted)
            return "$lapId: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $isLapCompleted\" (Yes)   "
        else
            return "$lapId: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $isLapCompleted\" (No)   "


    }


}

