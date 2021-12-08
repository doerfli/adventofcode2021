package day01

fun main() {
    val rawInput = ClassLoader.getSystemResource("day01/input.txt").readText()
    val increments = calculateIncrementWindow(rawInput)
    println(increments)
}

internal fun calculateIncrementWindow(rawInput: String): Int {
    val values = rawInput
        .split("\n")
        .filter { "" != it.trim() }
        .map { l -> l.toInt() }
//    values.forEach { println(it) }

    val windows = values.subList(2, values.size)
        .mapIndexed { i, e ->
            values[i] + values[i + 1] + values[i + 2]
        }

//    windows.forEach { println(it) }

    val increments = windows.subList(1, windows.size)
        .mapIndexed { i, e ->
            {
//                println("${values[i]} ${e}")
                windows[i] < e
            }
        }.count { it() }
    return increments
}
