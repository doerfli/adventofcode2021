package day13

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day13/input.txt").readText()
    print(foldAll(inputRawLines))
}

internal fun foldAll(inputRawLines: String): Int {
    var (paper, instructions) = parseInput(inputRawLines)

    for (fold in instructions.folds) {
        paper = fold(paper, fold)
    }

    printPaper(paper)

    return paper.dots.size
}

fun printPaper(paper: Paper) {
    println("x ${paper.maxX()} y ${paper.maxY()}")
    val map = Array(paper.maxY() + 1) { Array(paper.maxX() + 1) { " " } }

    paper.dots.forEach { map[it.y][it.x] = "#" }

    map.forEach { line ->
        run {
            line.forEach { print(it) }
            println()
        }
    }
}