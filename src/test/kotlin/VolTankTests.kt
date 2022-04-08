import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VolTankTests {

    @Test
    fun zeroHeight() {
        assertEquals(0, tankVol(0, 110, 3500))
    }

    @Test
    fun allZero() {
        assertEquals(0, tankVol(0, 0, 0))
    }

    @Test
    fun maxVolume() {
        assertEquals(1, tankVol(1, 1, 1))
        assertEquals(1121, tankVol(25, 25, 1121))
    }

    @Test
    fun volumeIsLessThanHalf() {
        assertEquals(1120, tankVol(40,100,3000))
        assertEquals(749, tankVol(32,150,4800))
    }

    @Test
    fun halfVolume() {
        assertEquals(1500, tankVol(50,100,3000))
        assertEquals(5, tankVol(5,10,10))
    }

    @Test
    fun volumeIsMoreThanHalf() {
        assertEquals(1879, tankVol(60,100,3000))
        assertEquals(625, tankVol(90,150,999))
    }
}