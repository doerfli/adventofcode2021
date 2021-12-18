package day14

fun main() {
    val inputRawLines = ClassLoader.getSystemResource("day14/input.txt").readText()
    print(processPolymer(inputRawLines))
}

internal fun processPolymer(inputRawLines: String): Int {
    val lines = inputRawLines.split("\n")
    val template = lines[0]
    val rules = parseRules(lines.subList(2, lines.size))

    val result = applyRules(template, rules, 10)
    val counts = result.toCharArray().asList()
        .groupingBy { it }
        .eachCount()
        .values
        .sortedDescending()

    return counts.first() - counts.last()
}

fun applyRules(template: String, rules: Map<String, String>, i: Int): String {
    var string = template
    (1..i).forEach { _ ->
        string = applyRules(string, rules)
    }
    return string
}

fun applyRules(template: String, rules: Map<String, String>): String {
    val chars = template.toCharArray()
    val result = StringBuilder()

    for((i, char) in chars.withIndex()) {
        if (i == 0) {
            result.append(char)
            continue
        }

        val pre = chars[i-1]
        val ruleKey = "$pre$char"
        if (rules.containsKey(ruleKey)) {
            result.append(rules[ruleKey]).append(char)
        } else {
            result.append(char)
        }
    }
    return result.toString()
}

fun parseRules(ruleLines: List<String>): Map<String, String> {
    return ruleLines.map {
        val t = it.split("->")
        Pair(it.substring(0, 2), it.substring(6))
    }.toMap()
}
