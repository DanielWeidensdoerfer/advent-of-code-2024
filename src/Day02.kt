import kotlin.math.abs

fun main() {
    readInput("Day02_test").run {
        println(part1() == 2)
        println(part2() == 4)
    }
    readInput("Day02").run {
        part1().println()
        part2().println()
    }
}

private fun List<String>.part1() = count { line ->
    val levels = line.split(" ").map { it.toInt() }
    levels.isSafe()
}


private fun List<String>.part2() = count { line ->
    val levels = line.split(" ").map { it.toInt() }
    val variations = List(levels.size) { i -> levels.toMutableList().apply { removeAt(i) } }
    levels.isSafe() || variations.any { it.isSafe() }
}

private fun List<Int>.isSafe() : Boolean {
    val levelsAsc = sorted()
    val levelsDesc = sortedDescending()
    val diffsValid = dropLast(1).mapIndexed { i, level -> abs(level - get(i + 1)) }.any { it == 0 || it > 3 }.not()

    return this in listOf(levelsDesc, levelsAsc) && diffsValid
}