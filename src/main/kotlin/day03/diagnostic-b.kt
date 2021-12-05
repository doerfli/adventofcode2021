package day03

fun main() {
    val lines = ClassLoader.getSystemResource("day03/input.txt").readText()
        .split("\n")
        .map { it.chars().toArray().map { Char(it).toString() } }

    val oxrBin = calculateRating(lines) { e1, e2 -> e1 > e2 }
    val oxr = oxrBin.toLong().convertBinaryToDecimal()

    val scrBin = calculateRating(lines) { e1, e2 -> e1 <= e2 }
    val scr = scrBin.toLong().convertBinaryToDecimal()

    println("$oxrBin $oxr / $scrBin $scr / ${oxr * scr}")

}

private fun calculateRating(input: List<List<String>>, compare: (e1: Int, e2: Int) -> Boolean): String {
    var arr = input.map { it }
    var n = 0

    while (arr.size > 1 && n < arr[0].size) {
        val zeroesAndOnes = arr.map { it[n] }.groupBy { it }
        if (compare(zeroesAndOnes["0"]?.size ?: 0, zeroesAndOnes["1"]?.size ?: 0)) {
            arr = arr.filter { it[n] == "0" }
        } else {
            arr = arr.filter { it[n] == "1" }
        }
        n += 1
    }

    return arr[0].joinToString("")
}
