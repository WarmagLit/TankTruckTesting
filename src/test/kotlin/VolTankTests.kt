import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertFailsWith

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
        assertAll("Filled tank",
            { assertEquals(1, tankVol(1, 1, 1)) },
            { assertEquals(1121, tankVol(25, 25, 1121)) }
        )
    }

    @Test
    fun volumeIsLessThanHalf() {
        assertAll("H < R",
            { assertEquals(1120, tankVol(40,100,3000)) },
            { assertEquals(749, tankVol(32,150,4800)) }
        )
    }

    @Test
    fun halfVolume() {
        assertAll("H == R",
            { assertEquals(1500, tankVol(50,100,3000))},
            { assertEquals(5, tankVol(5,10,10)) }
        )
    }

    @Test
    fun volumeIsMoreThanHalf() {
        assertAll("H > R",
            { assertEquals(1879, tankVol(60,100,3000)) },
            { assertEquals(625, tankVol(90,150,999)) }
        )
    }

    @Test
    fun negativeHeight() {
        assertAll(
            { assertFailsWith<Exception> { tankVol(-60,100,3000) } },
            { assertFailsWith<Exception> { tankVol(-999,100,3000) } }
        )
    }

    @Test
    fun negativeDiameter() {
        assertAll(
            { assertFailsWith<Exception> { tankVol(60,-100,3000) } },
            { assertFailsWith<Exception> { tankVol(60,-876,3000) } }
        )
    }

    @Test
    fun negativeVolume() {
        assertAll(
            { assertFailsWith<Exception> { tankVol(60,100,-3000) } },
            { assertFailsWith<Exception> { tankVol(60,100,-290) } }
        )
    }

}