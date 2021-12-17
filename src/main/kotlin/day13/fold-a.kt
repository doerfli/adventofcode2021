package day13

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day13/input.txt").readText()
    print(foldFirst(inputRawLines))
}

internal fun foldFirst(inputRawLines: String): Int {
    val (paper, instructions) = parseInput(inputRawLines)

    val foldedPaper = fold(paper, instructions.folds[0])
    return foldedPaper.dots.size
}

fun fold(paper: Paper, fold: Fold): Paper {
    val newDots = paper.dots.map { dot ->
        when(fold.direction) {
            "x" -> {
                if (dot.x < fold.position) {
                    dot
                } else {
                    Dot(fold.position - (dot.x - fold.position), dot.y)
                }
            }
            "y" -> {
                if (dot.y < fold.position) {
                    dot
                } else {
                    Dot(dot.x, fold.position - (dot.y - fold.position))
                }
            }
            else -> {
                throw IllegalArgumentException("fold direction: ${fold.direction}")
            }
        }
    }.distinct()
    return Paper(newDots)
}

fun parseInput(inputRawLines: String): Pair<Paper, Instructions> {
    val lines = inputRawLines.split("\n")
    val instructions = mutableListOf<Fold>()
    val dots = mutableListOf<Dot>()
    lines.forEach { line ->
        if (line.startsWith("fold along")) {
            if (line.contains("x=")) {
                instructions.add(Fold("x", line.split("=")[1].toInt()))
            } else {
                instructions.add(Fold("y", line.split("=")[1].toInt()))
            }
        } else {
            if (line == "") {
                return@forEach
            }
            val c = line.split(",")
            dots.add(Dot(c[0].toInt(), c[1].toInt()))
        }
    }

    return Pair(Paper(dots), Instructions(instructions))
}

data class Instructions(val folds: List<Fold>)
data class Fold(val direction: String, val position: Int)

data class Paper(val dots: List<Dot>)
data class Dot(val x: Int, val y: Int)