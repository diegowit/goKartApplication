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

}