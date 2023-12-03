import controller.KartAPI
import  models.Kart
import models.Lap
import utils.ScannerInput.readNextChar
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import mu.KotlinLogging
import persistence.JSONSerializer
import utils.ModelUtility.isValidmodel
import java.io.File
import utils.ScannerInput
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
            //6 -> runSearchMenu()
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
║                   CONTACT MENU                    ║
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

        println("Option Invalid - No notes stored")
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
     *  Karts
     *
     */

    fun runLapMenu() {
        do {
            when (val option = lapMenu()) {
                1 -> addLapToKart()
                2 -> listAllLaps()

                0 -> return // Return to main menu
                else -> println("Invalid menu choice: $option")
            }
        } while (true)
    }
    fun lapMenu(): Int {
        return readNextInt(
            """
╭───────────────────────────────────────────────────╮
│                   GROUP MENU                      │
├───────────────────────────────────────────────────┤
│   1) Add a Lap to Kart                            │
│                                                   │
├───────────────────────────────────────────────────┤
│   0) Return to Main Menu                          │
╰───────────────────────────────────────────────────╯
==>> """.trimMargin(">")
        )
    }









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






