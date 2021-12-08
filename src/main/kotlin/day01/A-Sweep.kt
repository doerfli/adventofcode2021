package day01

fun main() {
    val input = ClassLoader.getSystemResource("day01/input.txt").readText()
    val increments = calculateIncrements(input)

    println(increments)
}

internal fun calculateIncrements(input: String): Int {
    val values = input
        .split("\n")
        .filter { "" != it.trim() }
        .map { l -> l.toInt() }

    val increments = values.subList(1, values.size)
        .mapIndexed { i, e ->
            {
//                println("${values[i]} ${e}")
                values[i] < e
            }
        }.count { it() }
    return increments
}
