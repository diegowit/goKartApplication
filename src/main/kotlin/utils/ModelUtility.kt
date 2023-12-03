package utils

object ModelUtility {

    @JvmStatic
    val models = setOf ("Sodikart", "Tony Kart", "Birel", "Kosmic Kart", "Other")

    @JvmStatic
    fun isValidmodel(modelToCheck: String?): Boolean {
        for (model in models) {
            if (model.equals(modelToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

}