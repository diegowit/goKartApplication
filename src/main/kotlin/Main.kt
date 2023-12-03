import controller.KartAPI
import  models.Kart
import models.Lap
import utils.ScannerInput.readNextChar
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import mu.KotlinLogging
import persistence.JSONSerializer
import java.io.File
import utils.ScannerInput.readNextDouble
import utils.ValidateInput.readValidModel
import utils.ValidateInput.readValidWeight


import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}
private val kartAPI = KartAPI(JSONSerializer(File("Karts.json")))

fun main() = runMainMenu()

/**
 * Runs the main menu loop.
 */

fun runMainMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> runKartMenu()
            2 -> runLapMenu()
            0 -> exitApp()
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}



/**
 *
 *  Main Menu
 *
 */

fun mainMenu(): Int {
    return readNextInt(
        """
    
▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌     ▐░░▌▐░░░░░░░░░░░▌
▐░▌       ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌   ▐░▐░▌▐░█▀▀▀▀▀▀▀▀▀ 
▐░▌       ▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌▐░▌ ▐░▌▐░▌▐░▌          
▐░▌   ▄   ▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌ ▐░▐░▌ ▐░▌▐░█▄▄▄▄▄▄▄▄▄ 
▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░▌ ▐░▌░▌ ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌   ▀   ▐░▌▐░█▀▀▀▀▀▀▀▀▀ 
▐░▌▐░▌ ▐░▌▐░▌▐░▌          ▐░▌          ▐░▌          ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          
▐░▌░▌   ▐░▐░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░█▄▄▄▄▄▄▄▄▄ 
▐░░▌     ▐░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌
 ▀▀       ▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀ 
            
            
            
            
            
            
            
╔═════════════════════════════════════════════════════╗
║                 Go Karts System                     ║
╠═════════════════════════════════════════════════════╣
║ Welcome to the Go Karts System, where speed meets   ║
║ excitement! This app allows you to manage and track ║
║ information about your fleet of go-karts and monitor║
║ thrilling laps around the track. Get ready for an   ║
║ adrenaline-fueled experience!                       ║
╚═════════════════════════════════════════════════════╝
║                1.  Karts Menu                       ║
║                2.  Laps Menu                        ║
╚═════════════════════════════════════════════════════╝
║                0.     Exit                          ║
╚═════════════════════════════════════════════════════╝
==>> """.trimMargin()
    )
}


/**
 *
 *  Karts
 *
 */

fun runKartMenu() {
    do {
        when (val option = contactMenu()) {
            1 -> addKart()
            2 -> runListingMenu()
            3 -> updateKart()
            4 -> deleteKart()
            5 -> makeItElectric()
            6 -> runSearch()
            20 -> save()
            21 -> load()
            0 -> return // Return to main menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun contactMenu(): Int {
    return readNextInt(
        """
╔═══════════════════════════════════════════════════╗
║                   Kart MENU                       ║
╠═══════════════════════════════════════════════════╣
║   1) Add a Kart                                   ║
║   2) Run Listing Menu                             ║
║   3) Update a Kart                                ║
║   4) Delete a Kart                                ║
║   5) Make Kart Electric                           ║
║   6) run Search Menu                              ║
╟───────────────────────────────────────────────────╢
║   20) Save                                        ║
║   21) Load                                        ║
╚═══════════════════════════════════════════════════╝
║   0) Return to Main Menu                          ║
╚═══════════════════════════════════════════════════╝
==>> """.trimMargin(">")
    )
}

fun addKart() {
    val model = readValidModel("Enter the karts model (Tony Kart, Birel, Other)")
    val weight = readValidWeight("Enter a weight between (70 to 80 kg): ")
    val maxSpeed = readNextDouble("Enter the Kart Max Speed in kph ")
    val enginePower = readNextInt("Enter the Engine Power in Horsepower ")
    val isAdded = kartAPI.add(Kart(model = model, weight = weight, maxSpeed = maxSpeed,enginePower = enginePower ))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}


fun updateKart() {
    listAllKarts()
    if (kartAPI.numberOfKarts() > 0) {
        val id = readNextInt("Enter the id of the Kart to update: ")
        if (kartAPI.findKart(id) != null) {
            val model = readValidModel("Enter the karts model (Tony Kart, Birel, Other)")
            val weight = readValidWeight("Enter a weight between (70 to 80 kg): ")
            val maxSpeed = readNextDouble("Enter the Kart Max Speed in kph ")
            val enginePower = readNextInt("Enter the Engine Power in Horsepower ")

            if (kartAPI.updateKart(id, Kart(0, model, weight, maxSpeed, enginePower, false))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no Karts for this index number")
        }
    }
}

fun deleteKart() {
    if (kartAPI.numberOfKarts() > 0) {
        listAllKarts()

        val id = readNextInt("Enter the id of the Kart to delete: ")

        val deletedKart = kartAPI.deleteKart(id)
        if (deletedKart != null) {
            println("Delete Successful!")
            println("Deleted Kart Details: $deletedKart")
        } else {
            println("Delete NOT Successful. Kart with ID $id not found.")
        }
    } else {
        println("No karts available for deletion.")
    }
}

fun makeItElectric() {
    if (kartAPI.numberOfFuelKarts() > 0) {
        listAllKarts()

        val id = readNextInt("Enter the Id of the Electric Kart: ")

        if (kartAPI.isKartElectric(id)) {
            println("Conversion to Electric Successful!")
        } else {
            println("Conversion to Electric NOT Successful. Kart with ID $id not found or does not meet conversion criteria.")
        }
    } else {
        println("No fuel-based karts available for conversion.")
    }
}

fun runListingMenu() {

    if (kartAPI.numberOfKarts() > 0) {

        val option = readNextInt(
            """
                  > ----------------------------------
                  > |   1) View ALL Karts             |
                  > |   2) View Fuel Karts            |
                  > |   3) View Electric Karts        |
                  > |   4) List by                    |
                  > |   5) List By                     |
                  > ----------------------------------
                  > ==>> """.trimMargin(">"))


        when (option) {
            1 -> listAllKarts()
            2 -> listFuelKart()
            3 -> listElectricKart()
           // 4 -> runPriority()
           // 5 -> runReminder()
            // Handle unexpected option entries.
            else -> println("Invalid option entered: $option")
        }
    } else {

        println("Option Invalid - No Karts stored")
    }
}


fun listAllKarts() {
    println(kartAPI.listAllKart())
}


fun listFuelKart() {
    println(kartAPI.listFuelKarts())
}


fun listElectricKart() {
    println(kartAPI.listElectricKarts())
}


/**
 *
 *  Search Notes Menu
 *
 */
fun runSearch () {

    do {
        when (val option = searchMenu()) {
            1 ->  searchKartByModel()
            2 ->  searchKartByWeight()
            3 ->  searchKartByEnginePower()



            0 -> return // Return to main menu
            else -> println("Invalid menu choice: $option")
        }
    } while (true)
}

fun searchMenu(): Int {

    print(
        """
╔═══════════════════════════════════════════════════╗
║                   Search  Menu                    ║
╠═══════════════════════════════════════════════════╣
║   1) Search Kart By Model                         ║
║   2) Search Kart By Weight                        ║
║   3) Search Kart By Engine Power                  ║
║                                                   ║
║                                                   ║
║                                                   ║
╚═══════════════════════════════════════════════════╝
║   0) Return to Main Menu                          ║
╚═══════════════════════════════════════════════════╝
            
            
       ==>> """.trimMargin()
    )
    return readLine()!!.toInt()
    // Returning the user input as an integer.
}


fun searchKartByModel() {

    val searchModel = readNextLine("Enter the model to search by:(Tony Kart, Birel, Other) ")

    val searchResults = kartAPI.searchByModel(searchModel)

    if (searchResults.isEmpty()) {

        println("No Model found")
    } else {

        println(searchResults)
    }
}

fun searchKartByWeight() {
    val searchWeight = readNextInt("Enter Weight of the kart")

    val searchResults = kartAPI.searchByWeight(searchWeight)

    if (searchResults.isEmpty()) {
        println("No Kart found")
    } else {
        println(searchResults)
    }
}

fun searchKartByEnginePower(){
    val searchEP = readNextInt("Enter Engine Power of the kart")

    val searchResults = kartAPI.searchByEP(searchEP)

    if (searchResults.isEmpty()) {
        println("No Kart found")
    } else {
        println(searchResults)
    }
}



    /**
     *
     *  Karts
     *
     */

    fun runLapMenu() {
        do {
            when (val option = lapMenu()) {
                1 -> addLapToKart()
                2 -> deleteALap()
                3 -> updateLapDetailsInKart()
                4 -> markLapCompletion()
                5 -> runLapSearchMenu()

                0 -> return // Return to main menu
                else -> println("Invalid menu choice: $option")
            }
        } while (true)
    }

//fun listAllLaps() {
 //   val kart: Kart? = listingLapsForAll()
 //   println(kart)
//}


fun lapMenu(): Int {
        return readNextInt(
            """
╭───────────────────────────────────────────────────╮
│                   Lap MENU                        │
├───────────────────────────────────────────────────┤
│   1) Add a Lap to Kart                            │
│   2) update Lap details                           │
│   3) delete a lap                                 │
│   4) Mark Lap as a complete or not complete       │
│   5) Run Search Menu                              │
├───────────────────────────────────────────────────┤
│   0) Return to Main Menu                          │
╰───────────────────────────────────────────────────╯
==>> """.trimMargin(">")
        )

    }


fun addLapToKart() {
    val kart: Kart? = askUserToChooseFuelKart()
    if (kart != null) {
        val driverName = readNextLine("Enter driver name: ")
        val driverAge = readNextInt("Enter driver age: ")
        val distance = readNextDouble("Enter lap distance: ")
        val time = readNextDouble("Enter lap time: ")
        val speed = readNextDouble("Enter lap speed: ")
        val completedLapDetails = readNextLine("Enter lap details: ")

        val lapToAdd = Lap(
            driverName = driverName,
            driverAge = driverAge,
            distance = distance,
            time = time,
            speed = speed,
            completedLap = completedLapDetails
        )

        if (kart.addLap(lapToAdd)) {
            println("Add Successful!")
        } else {
            println("Add NOT Successful")
        }
    }
}


fun markLapCompletion() {
    val kart: Kart? = askUserToChooseFuelKart()
    if (kart != null) {
        val lap: Lap? = askUserToChooseLap(kart)
        if (lap != null) {
            var changeStatus = 'X'
            if (lap.isLapCompleted) {
                changeStatus = readNextChar("The lap is currently complete...do you want to mark it as Not Completed?")
                if ((changeStatus == 'Y') || (changeStatus == 'y')) {
                    lap.isLapCompleted = false
                    println("Marked lap as Not Completed.")
                }
            } else {
                changeStatus = readNextChar("The lap is currently Not Completed...do you want to mark it as Complete?")
                if ((changeStatus == 'Y') || (changeStatus == 'y')) {
                    lap.isLapCompleted = true
                    println("Marked lap as Complete.")
                }
            }
        }
    }
}
fun deleteALap() {
    val kart: Kart? = askUserToChooseFuelKart()
    if (kart != null) {
        val lap: Lap? = askUserToChooseLap(kart)
        if (lap != null) {
            val isDeleted = kart.deleteLap(lap.lapId)
            if (isDeleted) {
                println("Delete Successful!")
            } else {
                println("Delete NOT Successful")
            }
        }
    }
}
fun updateLapDetailsInKart() {
    val kart: Kart? = askUserToChooseFuelKart()
    if (kart != null) {
        val lap: Lap? = askUserToChooseLap(kart)
        if (lap != null) {
            val newDetails = readNextLine("Enter new lap details: ")
            val updatedLap = Lap(
                lapId = lap.lapId,
                driverName = lap.driverName,
                driverAge = lap.driverAge,
                distance = lap.distance,
                time = lap.time,
                speed = lap.speed,
                completedLap = newDetails,
                isLapCompleted = lap.isLapCompleted
            )
            if (kart.updateLap(lap.lapId, updatedLap)) {
                println("Lap details updated")
            } else {
                println("Lap details NOT updated")
            }
        } else {
            println("Invalid Lap Id")
        }
    }
}

/**
 *
 *  Lap Search
 *
 */

fun runLapSearchMenu() {

    if (kartAPI.numberOfKarts() > 0) {

        val option = readNextInt(
            """
                  > ----------------------------------
                  > |   1) search Laps              |
                  > |   2) List Not Completed Laps  |
                  > |   3) Search By Driver Name    |
                  > ----------------------------------
                  > ==>> """.trimMargin(">"))


        when (option) {
            1 -> searchLaps()
            2 -> listNotCompletedLaps()
            3 -> searchByName()


            // Handle unexpected option entries.
            else -> println("Invalid option entered: $option")
        }
    } else {

        println("Option Invalid - No Laps stored")
    }
}

fun searchLaps() {
    val searchDetails = readNextLine("Enter lap details to search by: ")
    val searchResults = kartAPI.SearchLapsNotCompleted(searchDetails)
    if (searchResults.isEmpty()) {
        println("No laps found")
    } else {
        println(searchResults)
    }
}


fun listNotCompletedLaps() {
  if (kartAPI.numberOfNotCompletedLaps() > 0){
      println("Total of not completed laps: ${kartAPI.numberOfNotCompletedLaps()}")
  }
    println(kartAPI.listFuelKarts())
}

fun searchByName(){
    val searchName = readNextLine("Enter the Name of the Driver: ")
    val searchResults = kartAPI.searchLapByDriverName(searchName)
    if (searchResults.isEmpty()) {
        println("No Laps found")
    } else {
        println(searchResults)
    }
}

//------------------------------------
//HELPER FUNCTIONS
//------------------------------------

private fun askUserToChooseFuelKart(): Kart? {
    listFuelKart()
    if (kartAPI.numberOfFuelKarts() > 0) {
        val kart = kartAPI.findKart(readNextInt("\nEnter the id of the Kart: "))
        if (kart != null) {
            if (kart.isElectric) {
                println("Kart is NOT Fuel, it is Electric")
            } else {
                return kart //chosen kart is fuel
            }
        } else {
            println("Kart id is not valid")
        }
    }
    return null //selected Kart is not active
}



    private fun askUserToChooseLap(kart: Kart): Lap? {
        return if (kart.numberOfLaps() > 0) {
            print(kart.listLaps())
            kart.findOne(readNextInt("\nEnter the id of the Lap: "))
        } else {
            println("No Laps for chosen note")
            null
        }
    }
/**
private fun listingLapsForAll(kart: Kart): Kart? {
    if (kart.numberOfLaps() > 0) {
        println(kart.listLaps())
        println("\nAll Laps ")
    } else {
        println("No Laps stored")
    }

}
**/
    fun save() {
        try {
            kartAPI.store()
        } catch (e: Exception) {
            System.err.println("Error writing to file: $e")
        }
    }

    fun load() {
        try {
            kartAPI.load()
        } catch (e: Exception) {
            System.err.println("Error reading from file: $e")
        }
    }

    fun exitApp() {
        println(
            "_______  _______  _______    __   __  _______  __   __    _______  _______  _______  __    _ \n" +
                    "|       ||       ||       |  |  | |  ||       ||  | |  |  |       ||       ||       ||  |  | |\n" +
                    "|  _____||    ___||    ___|  |  |_|  ||   _   ||  | |  |  |  _____||   _   ||   _   ||   |_| |\n" +
                    "| |_____ |   |___ |   |___   |       ||  | |  ||  |_|  |  | |_____ |  | |  ||  | |  ||       |\n" +
                    "|_____  ||    ___||    ___|  |_     _||  |_|  ||       |  |_____  ||  |_|  ||  |_|  ||  _    |\n" +
                    " _____| ||   |___ |   |___     |   |  |       ||       |   _____| ||       ||       || | |   |\n" +
                    "|_______||_______||_______|    |___|  |_______||_______|  |_______||_______||_______||_|  |__|\n" +
                    "                                                                                              \n" +
                    "\n" +
                    "      Thank you for using This App!\n" +
                    "Exiting... Have a great day!"
        )
        exitProcess(0)
    }







