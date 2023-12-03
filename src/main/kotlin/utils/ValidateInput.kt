package utils
import utils.ScannerInput.readNextInt
import java.util.*

object ValidateInput {

    @JvmStatic
    fun readValidModel(prompt: String?): String {
        print(prompt)
        var input = Scanner(System.`in`).nextLine()
        do {
            if (ModelUtility.isValidmodel(input))
                return input
            else {
                print("Invalid Kart Model $input.  try again: ")
                input = Scanner(System.`in`).nextLine()
            }
        } while (true)
    }

    @JvmStatic
    fun readValidWeight(prompt: String?): Int {
        var input = readNextInt(prompt)
        do {
            if (Utilities.validRange(input, 70, 80))
                return input
            else {
                print("Invalid weight $input.")
                input = readNextInt(prompt)
            }
        } while (true)
    }
}
