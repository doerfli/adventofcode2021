package day12

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day12/input.txt").readText()
    print(findPaths(inputRawLines))
}

internal fun findPaths(inputRawLines: String): Int {
    val map = parseInput(inputRawLines.split("\n"))

    val paths = mutableSetOf<List<Cave>>()
    walk(map.entrance, paths, mutableListOf<Cave>())

    paths.forEach { println(it.joinToString(",") { cave -> cave.name }) }

    return paths.size
}

fun walk(current: Cave, foundPaths: MutableSet<List<Cave>>, visited: MutableList<Cave>) {
    val nowVisited = visited.toMutableList()
    nowVisited.add(current)

    if (current.name == "end") {
        foundPaths.add(nowVisited)
        return
    }

    current.neighbours.forEach { cave ->
        // check if not visited before
        if (cave is SmallCave && nowVisited.contains(cave)) {
            return@forEach
        }
        walk(cave, foundPaths, nowVisited)
    }
}

fun parseInput(lines: List<String>): Map {
    val nodes = mutableMapOf<String, Cave>()

    lines.forEach { line ->
        val values = line.split("-")
        val node1 = nodes.getOrPut(values[0]) { createCave(values[0]) }
        val node2 = nodes.getOrPut(values[1]) { createCave(values[1]) }
        node1.neighbours.add(node2)
        node2.neighbours.add(node1)
    }

    return Map(nodes["start"]!!)
}

fun createCave(s: String): Cave {
    return if (s[0].isUpperCase()) {
        BigCave(s, mutableListOf())
    } else {
        SmallCave(s, mutableListOf())
    }
}

data class Map(val entrance: Cave)
sealed class Cave(val name: String, val neighbours: MutableList<Cave>)
class SmallCave(name: String, neighbours: MutableList<Cave>): Cave(name, neighbours)
class BigCave(name: String, neighbours: MutableList<Cave>): Cave(name, neighbours)