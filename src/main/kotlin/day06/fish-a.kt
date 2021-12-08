package day06

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day06/input.txt").readText()
    print(calculateNumberOfFishA(inputRawLines, 80))
}

internal fun calculateNumberOfFishA(inputRawLines: String, days: Int): ULong {
    var fishState = inputRawLines.split(",").map { it.toInt() }

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

    return fishState.size.toULong()
}