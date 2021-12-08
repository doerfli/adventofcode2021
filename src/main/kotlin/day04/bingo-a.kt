package day04

import java.util.NoSuchElementException

fun main() {
    val rawInput = ClassLoader.getSystemResource("day04/input.txt").readText()
    calculateBingoScore(rawInput)
}

internal fun calculateBingoScore(rawInput: String): Int {
    val lines = rawInput
        .split("\n")
    val numbers = lines[0].split(',').map { it.toInt() }
    val boards = parseBoards(lines.subList(2, lines.size))

    val (winningBoard, winningNumber) = play(boards, numbers)
    println("Board won with number $winningNumber")
    println(winningBoard)
    val sumOfAllUnmarkedNumbers =
        winningBoard.rows.map { r -> r.filter { !it.marked } }.flatten().map { it.number }.sum()
    val score = sumOfAllUnmarkedNumbers * winningNumber
    println("Score: $score")
    return score
}

fun play(boards: List<Board>, numbers: List<Int>): Pair<Board, Int> {
    for (number in numbers) {
        for (board in boards) {
            board.play(number)
            if (board.wins()) {
                return Pair(board, number)
            }
        }
    }
    throw NoSuchElementException("no winning board found")
}

fun parseBoards(lines: List<String>): List<Board> {
    var n = 1
    val boards = mutableListOf<Board>()
    while (n * 6 <= lines.size) {
        val rows = lines.subList((n-1) * 6, n * 6).subList(0, 5)
        boards.add(Board(parseRows(rows)))
        n += 1
    }
    return boards
}

fun parseRows(rows: List<String>): List<List<Field>> {
    return rows.map { row ->
        row.split(" ").filter { "" != it }.map { f ->
            Field(f.toInt(), false)
        }
    }
}

class Board(val rows: List<List<Field>>, var won: Boolean = false, var wonWithNumber: Int = -1) {
    override fun toString(): String {
        return rows.joinToString(separator = "\n") { it.joinToString(separator = " ") }
    }

    fun play(number: Int) {
        if (won) {
            // no need to continue if board is already won
            return
        }
        if (! hasNumber(number)) {
            return
        }
        mark(number)
        if (isWon()) {
            won = true
            wonWithNumber = number
        }
    }

    private fun mark(number: Int) {
        rows.flatten().find { it.number == number }?.marked = true
    }

    private fun hasNumber(number: Int): Boolean {
        return rows.flatten().map { it.number }.contains(number)
    }

    fun wins(): Boolean {
        return won;
    }

    private fun isWon(): Boolean {
        val wonByRow = rows.map { row -> row.map { field -> field.marked }.all { it } }.find { it } ?: false
        if (wonByRow) {
            return true;
        }

        return (0..4).map { col ->
            rows.map { r -> r[col].marked }.all{ it }
        }.find{ it } ?: false
    }
}

data class Field(val number: Int, var marked: Boolean) {
    override fun toString(): String {
        val n = number.toString().padStart(2, ' ')
        if (marked) {
            return "*$n"
        }
        return " $n"
    }
}

