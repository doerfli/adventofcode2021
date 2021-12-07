package day06

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day06/input.txt").readText()
    var fishState = inputRawLines.split(",").map { it.toInt() }

    val days = 80
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
            while(t < days) {
                offspring += 1uL
                if (offSpringCount.containsKey(t + firstReproCycle)) {
                    offspring += offSpringCount[t + firstReproCycle]!!
                }
                t += reproCycle
            }
            offSpringCount[day] = offspring
        }
    }

    val count = fishState.size.toULong() + fishState.map { offSpringCount[it]!! }.sumOf { it }
    println(count)

}

private fun isLastDay(days: Int, day: Int) = days == day