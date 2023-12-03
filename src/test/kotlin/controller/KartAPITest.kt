package controller
import com.thoughtworks.xstream.mapper.Mapper.Null
import models.Kart
import models.Lap
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
class KartAPITest {
    private var Tony: Kart? = null
    private var Birel: Kart? = null
    private var Max: Kart? = null
    private val populatedKarts: KartAPI = KartAPI(JSONSerializer(File("karts.json")))
    private val emptyKarts: KartAPI = KartAPI(JSONSerializer(File("karts.json")))


    @BeforeEach
    fun setup(){
        Tony = Kart(0, "Tony", 15, 47.0, 5, false )
        Birel = Kart(0, "Birel", 25, 45.0, 6, false )
        Max = Kart(0, "Max", 30, 46.0, 7, true)

        Tony!!.laps.add(Lap(1,"jose",50,4.9,6.5,50.5,"5",true,))
        Birel!!.laps.add(Lap(2,"maria",15,6.9,8.5,50.6,"7",false,))
        Max !!.laps.add(Lap(3,"gabriel",25,7.9,2.5,50.7,"5",true,))


        // adding 3 Karts to the karts api
        populatedKarts!!.add(Tony!!)
        populatedKarts!!.add(Birel!!)
        populatedKarts!!.add(Max!!)


    }






}