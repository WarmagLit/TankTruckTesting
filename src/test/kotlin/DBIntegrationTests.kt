import com.example.getInputByID
import com.example.writeInputParameters
import com.exampleimport.InputData
import com.exampleimport.Inputs
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFailsWith

class DBIntegrationTests {
/*
    @Test
    fun loadDatabase() {
        assertDoesNotThrow {
            Database.connect("jdbc:postgresql://localhost:5432/testingdb", driver = "org.postgresql.Driver",
                user = "hits", password = "hitsthebest")

            transaction {
                SchemaUtils.create(Inputs)
            }
        }
    }

    @Test
    fun writeDataAndCheck() {
        var input = InputData("0",0,0,0)
        assertDoesNotThrow {
            transaction {
                input = writeInputParameters(1, 1, 1)
            }
        }

        assertTrue(input == getInputByID(input.uuid))
    }
*/
    @Test
    fun nullSearchCheck() {
        assertEquals(null ,getInputByID("0"))
    }



}