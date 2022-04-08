import kotlin.math.asin
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    println(tankVol(60,100,3000))

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

fun tankVol(h:Int, d:Int, vt:Int): Int {
    val R: Double = d / 2.0
    val H: Double = h.toDouble()
    var res: Double = 0.0
    var trigArea: Double = 0.0
    var sectorArea: Double = 0.0

    val length: Double = vt/(Math.PI * Math.pow(R, 2.0))
    val b: Double = Math.sqrt(Math.pow(R, 2.0) - Math.pow((R - H), 2.0)) * 2
    val alpha: Double = Math.asin((b/2)/R) * 2 // In radians

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
