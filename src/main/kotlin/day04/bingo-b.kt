package day04

import java.util.NoSuchElementException

fun main() {
    val rawInput = ClassLoader.getSystemResource("day04/input.txt").readText()
    calculateBingoScoreLastBoard(rawInput)
}

internal fun calculateBingoScoreLastBoard(rawInput: String): Int {
    val lines = rawInput
        .split("\n")
    val numbers = lines[0].split(',').map { it.toInt() }
    val boards = parseBoards(lines.subList(2, lines.size))

    val (winningBoard, winningNumber) = playUntilLastBoardIsWon(boards, numbers)
    println("Last board won with number $winningNumber")
    println(winningBoard)

    // calculate score
    val sumOfAllUnmarkedNumbers =
        winningBoard.rows.map { r -> r.filter { !it.marked } }.flatten().map { it.number }.sum()
    val score = sumOfAllUnmarkedNumbers * winningNumber
    println("Score: $score")

    return score
}

fun playUntilLastBoardIsWon(boards: List<Board>, numbers: List<Int>): Pair<Board, Int> {
    for (number in numbers) {
        for (board in boards) {
            board.play(number)
        }
    }

    val wonBoards = boards.filter { it.won }
    for (number in numbers.asReversed()) {
        val boardWonWithNumber = wonBoards.find { it.wonWithNumber == number }
        if (boardWonWithNumber != null) {
            return Pair(boardWonWithNumber, number)
        }
    }

    throw NoSuchElementException("board not found")
}

