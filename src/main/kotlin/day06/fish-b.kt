package day06

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day06/input.txt").readText()
    val count = calculateNumberOfFishB(inputRawLines, 256)
    println(count)
}

internal fun calculateNumberOfFishB(inputRawLines: String, days: Int): ULong {
    val fishState = inputRawLines.split(",").map { it.toInt() }

    val offSpringCount = mutableMapOf<Int, ULong>()
    val firstReproCycle = 9
    val reproCycle = 7

    (1..days).reversed().forEach { day ->
        if (isLastDay(days, day)) { // last day of period
            offSpringCount[day] = 0u
        } else {
            var offspring = 0uL
            var t = day
            // calculate his own offspring
            while (t < days) {
                offspring += 1uL
                if (offSpringCount.containsKey(t + firstReproCycle)) {
                    offspring += offSpringCount[t + firstReproCycle]!!
                }
                t += reproCycle
            }
            offSpringCount[day] = offspring
        }
    }

    return fishState.size.toULong() + fishState.map { offSpringCount[it]!! }.sumOf { it }
}

private fun isLastDay(days: Int, day: Int) = days == day