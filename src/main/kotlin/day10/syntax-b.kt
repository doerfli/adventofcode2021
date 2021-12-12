package day10

import util.pop
import util.push


fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day10/input.txt").readText()
    print(completionScore(inputRawLines))
}

internal fun completionScore(inputRawLines: String): Long {
    val incompleteLines = inputRawLines.split("\n").map { line -> line.trim().toCharArray().map { intChar -> intChar.toString() } }
        .filter { line -> findCorruptChar(line).isEmpty }

    val scores = incompleteLines.map { line -> calculateCompletionScore(line)}.sortedDescending()
    return scores[scores.size / 2]
}

fun calculateCompletionScore(chars: List<String>): Long {
    val stack = mutableListOf<String>()

    chars.forEach { char ->
        when(char) {
            "(", "[", "{", "<" -> stack.push(char)
            else -> stack.pop()
        }
    }

    val completion = mutableListOf<String>()

    while(stack.isNotEmpty()) {
        when(stack.pop()) {
            "(" -> completion.add(")")
            "[" -> completion.add("]")
            "{" -> completion.add("}")
            "<" -> completion.add(">")
        }
    }

    return completion.map { char -> when(char) {
        ")" -> 1L
        "]" -> 2L
        "}" -> 3L
        ">" -> 4L
        else -> throw IllegalArgumentException(char)
    }}.reduce { acc, score -> acc * 5 + score }
}


