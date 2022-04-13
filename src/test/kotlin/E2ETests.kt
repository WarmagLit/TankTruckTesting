import com.example.tankVol
import com.example.view.MyController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class E2ETests {

    @Test
    fun checkViaTheController() {
        Assertions.assertAll("H < R",
            { assertEquals(1, MyController().calculateTankVol(1,1,1)) },
            { assertEquals(749, MyController().calculateTankVol(32,150,4800)) }
        )
    }
}