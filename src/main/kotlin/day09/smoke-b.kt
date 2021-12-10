package day09

import java.util.*

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day09/input.txt").readText()
    print(basinScore(inputRawLines))
}

var nxtBasinId = 0

internal fun basinScore(inputRawLines: String): Int {
    val heightMap = heightMap(inputRawLines)
    val maxY = heightMap.size - 1
    val maxX = heightMap[0].size - 1

    heightMap.forEachIndexed { yIndex, fields ->
        fields.forEachIndexed { xIndex, field ->
            evaluateField(field, xIndex, yIndex, heightMap, maxX, maxY)
        }
    }

    val lowPoints = heightMap.map { row -> row.filter { it.isCandiate } }.flatten()

    // floodfill
    lowPoints.forEachIndexed{ i, point -> floodFill(point, i, heightMap) }

    val threeLargestbasins = heightMap
        .map { row -> row.filter { it.basinLabel > -1 } }.flatten()
        .groupBy { it.basinLabel } // map - basinId -> fields
        .values
        .map { it.size } // count basin sizes
        .sortedDescending() // take three largest
        .take(3)

    return threeLargestbasins.reduce { acc, i -> acc * i }
}



fun floodFill(start: Field, basinId:Int, heightMap: List<List<Field>>) {
    val fields = Stack<Field>()
    fields.push(start)
    val maxY = heightMap.size - 1
    val maxX = heightMap[0].size - 1

    while (fields.isNotEmpty()) {
        val field = fields.pop()
        field.basinLabel = basinId

        if (field.x > 0) {
            val neighbour = heightMap[field.y][field.x - 1]
            addNeighbourUnlessRidge(neighbour, fields)
        }
        if (field.x < maxX) {
            val neighbour = heightMap[field.y][field.x + 1]
            addNeighbourUnlessRidge(neighbour, fields)
        }
        if (field.y > 0) {
            val neighbour = heightMap[field.y - 1][field.x]
            addNeighbourUnlessRidge(neighbour, fields)
        }
        if (field.y < maxY) {
            val neighbour = heightMap[field.y + 1][field.x]
            addNeighbourUnlessRidge(neighbour, fields)
        }
    }
}

private fun addNeighbourUnlessRidge(neighbour: Field, fields: java.util.Stack<Field>) {
    if (neighbour.height < 9 && neighbour.basinLabel == -1) {
        fields.push(neighbour)
    }
}

