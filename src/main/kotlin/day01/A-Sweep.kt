package day01

fun main() {
    val values = ClassLoader.getSystemResource("day01/input.txt").readText()
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

    println(increments)
}
