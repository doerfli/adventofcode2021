package day12

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day12/input.txt").readText()
    print(findPaths(inputRawLines))
}

internal fun findPathsSpecial(inputRawLines: String): Int {
    val map = parseInput(inputRawLines.split("\n"))
    val smallCaves = map.smallCaves.filter { it.name != "start" }.filter { it.name != "end" }

    val paths = mutableSetOf<List<Cave>>()

    smallCaves.forEach { smallCave ->
        walk(map.entrance, smallCave, paths, mutableListOf())
    }

//    paths.forEach { println(it.joinToString(",") { cave -> cave.name }) }
    return paths.size
}

private fun walk(current: Cave, specialCave: Cave, foundPaths: MutableSet<List<Cave>>, visited: MutableList<Cave>) {
    if (current == specialCave && visited.count { it == specialCave } > 1) {
        return
    } else {
        // check if not visited before
        if (current != specialCave && current is SmallCave && visited.contains(current)) {
            return
        }
    }

    val nowVisited = visited.toMutableList()
    nowVisited.add(current)

    if (current.name == "end") {
        foundPaths.add(nowVisited)
        return
    }

    current.neighbours.forEach { cave ->
        walk(cave, specialCave, foundPaths, nowVisited)
    }
}
