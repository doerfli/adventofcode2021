package day10

import util.pop
import util.push
import java.util.*


fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day10/input.txt").readText()
    print(syntaxScore(inputRawLines))
}

internal fun syntaxScore(inputRawLines: String): Int {
    return inputRawLines.split("\n").map { line ->
        parseLine(line.trim().toCharArray().map { it.toString() })
    }.sum()
}

fun parseLine(chunks: List<String>): Int {
    val openChunks = mutableListOf<String>()
    var unmatched: Optional<String> = Optional.empty()

    chunker@ for (chunk in chunks) {
        when(chunk) {
            "(","[","{","<" -> openChunks.push(chunk)
            else -> {
                val opener = openChunks.pop()
                if ("(" == opener && ")" == chunk) {
                    // do nothing
                } else if ("[" == opener && "]" == chunk) {
                    // do nothing
                } else if ("{" == opener && "}" == chunk) {
                    // do nothing
                } else if ("<" == opener && ">" == chunk) {
                    // do nothing
                } else {
                    // unmatched
                    unmatched = Optional.of(chunk)
                    break@chunker
                }
            }
        }
    }

    if (unmatched.isEmpty) {
        return 0
    }

    return when(unmatched.get()) {
        ")" -> 3
        "]" -> 57
        "}" -> 1197
        ">" -> 25137
        else -> throw IllegalArgumentException("unexpected char ${unmatched.get()}")
    }
}
