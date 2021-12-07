package day06

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day06/sample.txt").readText()
    var fishState = inputRawLines.split(",").map { it.toInt() }

    val days = 80

    (1..days).forEach { day ->
        val newFish = mutableListOf<Int>()
        fishState = fishState.map { timer ->
            if (timer > 0) {
                timer - 1
            } else {
                newFish.add(8)
                6
            }
        }
        fishState = fishState.plus(newFish)

//        println("${fishState.size.toString().padStart(6)} - ${fishState.joinToString(separator = ",")}")
        println("$day - ${fishState.size}")
    }


}