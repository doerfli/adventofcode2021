package day03

fun main() {
    val lines = ClassLoader.getSystemResource("day03/input.txt").readText()
        .split("\n")
        .map { it.chars().toArray().map { Char(it).toString() } }

    // transpose lines
    val transposed = Array(lines[0].size) { Array(lines.size) { "0" } }
    for ( x in lines.indices ) {
        for (y in lines[x].indices) {
            transposed[y][x] = lines[x][y]
        }
    }

    var gamma = ""
    var epsilon = ""

    for ( x in transposed ) {
        val groups = x.groupBy { it }
        val zeros = groups["0"]?.size!!
        val ones = groups["1"]?.size!!

        if (zeros > ones) {
            gamma += "0"
            epsilon += "1"
        } else {
            gamma += "1"
            epsilon += "0"
        }
    }

    val gammtInt = gamma.toLong().convertBinaryToDecimal()
    val epsilonInt =epsilon.toLong().convertBinaryToDecimal()
    println("gamma $gamma $gammtInt epsilon $epsilon $epsilonInt")
    println(gammtInt * epsilonInt)
}

fun Long.convertBinaryToDecimal(): Int {
    var num = this
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

