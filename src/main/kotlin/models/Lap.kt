package models

data class Lap (
    var lapId: Int = 0,
    var driverName: String,
    var driverAge: Int,
    var distance: Double,
    val time: Double,
    val speed: Double,
    val lapCompleted: Boolean

        )

