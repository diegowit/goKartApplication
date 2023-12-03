package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import controller.KartAPI
import models.Kart
import models.Lap

class UtilitiesTest {


    @Test
    fun validRangeWorksWithPositiveTestData() {
        Assertions.assertTrue(Utilities.validRange(5, 0, 10))
    }

    @Test
    fun validRangeWorksWithNegativeTestData() {
        Assertions.assertFalse(Utilities.validRange(15, 0, 10))
    }
}