import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertFailsWith

class DBIntegrationTests {

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

    @Test
    fun nullSearchCheck() {
        assertEquals(null ,getInputByID("0"))
    }



}