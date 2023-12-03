package models

data class Lap (
    var lapId: Int = 0,
    var driverName: String,
    var driverAge: Int,
    var distance: Double,
    var time: Double,
    var speed: Double,
    var completedLab: String,
    var isLapCompleted: Boolean = false

        )

{
    override fun toString(): String {

        if (isLapCompleted)
            return "$lapId: Name: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $completedLab:\" (Lap Was Complete)   "
        else
            return "$lapId: Name: $driverName, Age: $driverAge, Distance: $distance, Time: $time, Speed: $speed, Completed: $completedLab:\" (Lap Was not Complete)   "


    }


}

