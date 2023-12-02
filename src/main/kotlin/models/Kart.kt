package models

data class Kart(

    var Kartid: Int = 0,
    var model: String ="Birel",
    var weight: Int,
    var maxSpeed: Double,
    var enginePower: Int,
    var isElectric: Boolean,
    var laps: MutableSet<Lap> = mutableSetOf()

)





