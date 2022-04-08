import org.jetbrains.exposed.sql.ResultRow

data class InputData(
    val uuid: String,
    val h: Int,
    val d: Int,
    val vt: Int
){

    companion object {

        fun fromRow(resultRow: ResultRow) = InputData(
            uuid = resultRow[Inputs.uuid],
            h = resultRow[Inputs.height],
            d = resultRow[Inputs.diameter],
            vt = resultRow[Inputs.volume]
        )
    }
}
