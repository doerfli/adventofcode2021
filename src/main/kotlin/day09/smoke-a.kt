package day09

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day09/input.txt").readText()
    print(lowPointRisk(inputRawLines))
}

internal fun lowPointRisk(inputRawLines: String): Int {
    val heightMap = heightMap(inputRawLines)
    val maxY = heightMap.size - 1
    val maxX = heightMap[0].size - 1

    printMap(heightMap)

    heightMap.forEachIndexed { yIndex, fields ->
        fields.forEachIndexed { xIndex, field ->
            evaluateField(field, xIndex, yIndex, heightMap, maxX, maxY)
        }
    }

    return heightMap.map { rows -> rows.filter { it.isCandiate }.map { it.height + 1 } }.flatten().sum()
}

fun evaluateField(field: Field, xIndex: Int, yIndex: Int, heightMap: List<List<Field>>, maxX: Int, maxY: Int) {
    if ( ! field.isCandiate) {
        return
    }

    if (xIndex > 0) {
        val neighbour = heightMap[yIndex][xIndex - 1]
        if (! isLowerThanNeighbour(neighbour, field)) return
    }
    if (xIndex < maxX) {
        val neighbour = heightMap[yIndex][xIndex + 1]
        if (! isLowerThanNeighbour(neighbour, field)) return
    }
    if (yIndex > 0) {
        val neighbour = heightMap[yIndex - 1][xIndex]
        if (! isLowerThanNeighbour(neighbour, field)) return
    }
    if (yIndex < maxY) {
        val neighbour = heightMap[yIndex + 1][xIndex]
        if (! isLowerThanNeighbour(neighbour, field)) return
    }

}

private fun isLowerThanNeighbour(neighbour: Field, field: Field): Boolean {
    if (neighbour.height <= field.height) {
        field.isCandiate = false
        return false
    } else {
        neighbour.isCandiate = false
    }
    return true
}

private fun printMap(heightMap: List<List<Field>>) {
    heightMap.forEach { row ->
        run {
            row.forEach { field ->
                print(field.height)
            }
            println()
        }
    }
}

private fun heightMap(inputRawLines: String): List<List<Field>> {
    val heightMap = inputRawLines.split("\n")
        .map { row -> row.toCharArray().map { char -> Field(char.toString().toInt(), true) } }
    return heightMap
}

data class Field(val height: Int, var isCandiate: Boolean)
