import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main(args: Array<String>) {
    Database.connect("jdbc:postgresql://localhost:5432/testingdb", driver = "org.postgresql.Driver",
        user = "hits", password = "hitsthebest")

    transaction {
        SchemaUtils.create(Inputs)
    }
    val input = writeInputParameters(1,1,1)
    println(tankVolByInput(input))
    println(tankVol(1,1,3000))

}

fun writeInputParameters(h:Int, d:Int, vt:Int): InputData {
    val input = InputData(UUID.randomUUID().toString(), h, d, vt)
    transaction {
        Inputs.insert {
            it[uuid] = input.uuid
            it[height] = input.h
            it[diameter] = input.d
            it[volume] = input.vt
        }
    }
    return input
}

fun getInputByID(id: String): InputData? {
    val res = transaction {
        Inputs.select { Inputs.uuid eq id }.map { InputData.fromRow(it) }
    }
    if (res.isEmpty()) {
        return null
    }
    if (res.size > 1) {
        throw Exception("Database Error")
    }
    return res[0]
}

fun tankVolByInput(inputData: InputData): Int {
    return tankVol(inputData.h, inputData.d, inputData.vt)
}

fun tankVol(h:Int, d:Int, vt:Int): Int {
    val R: Double = d / 2.0
    val H: Double = h.toDouble()
    var res: Double
    val trigArea: Double
    val sectorArea: Double

    val length: Double = vt/(Math.PI * Math.pow(R, 2.0))
    val b: Double = Math.sqrt(Math.pow(R, 2.0) - Math.pow((R - H), 2.0)) * 2
    val alpha: Double = Math.asin((b/2)/R) * 2 // In radians

    if (h < 0)
        throw Exception("Height less than zero")
    if (d < 0)
        throw Exception("Diameter is less than zero")
    if (vt < 0)
        throw Exception("Volume is less than zero")
    if (h > d)
        throw Exception("Height is greater than diameter")


    if (h <= R) {
        trigArea = (R - h) * b / 2

        sectorArea = Math.pow(R, 2.0) * alpha / 2

        res = sectorArea - trigArea
        res *= length
    } else {
        trigArea = (h - R) * b / 2

        sectorArea = Math.pow(R, 2.0) * alpha / 2

        res = Math.PI * Math.pow(R, 2.0) - (sectorArea - trigArea)
        res *= length
    }

    return res.toInt()
}
