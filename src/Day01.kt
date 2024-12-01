import kotlin.math.abs

fun main() {
    readInput("Day01_test").run {
        println(part1() == 11)
        println(part2() == 31)
    }
    readInput("Day01").run {
        part1().println()
        part2().println()
    }
}

private val spaces = Regex(" +")

private fun List<String>.part1(): Int {
    val numPairs = map { line ->
        val nums = line.split(spaces).map { it.toInt() }
        nums[0] to nums[1]
    }
    val firstList = numPairs.map { it.first }.sorted()
    val secondList = numPairs.map { it.second }.sorted()
    val distsSum = firstList.zip(secondList).sumOf { (first, second) ->
        abs(first - second)
    }
    return distsSum
}

private fun List<String>.part2(): Int {
    val numPairs = map { line ->
        val nums = line.split(spaces).map { it.toInt() }
        nums[0] to nums[1]
    }
    val firstList = numPairs.map { it.first }.sorted()
    val secondList = numPairs.map { it.second }.sorted()
    val occurencesSum = firstList.sumOf { firstNum ->
        val occurences = secondList.count { secondNum -> firstNum == secondNum  }
        firstNum * occurences
    }
    return occurencesSum
}