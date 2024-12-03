fun main() {
    readInput("Day03_test_1").run { println(part1() == 161) }
    readInput("Day03_test_2").run { println(part2() == 48) }
    readInput("Day03").run {
        part1().println()
        part2().println()
    }
}

private val mulRegex = "mul\\((\\d+),(\\d+)\\)".toRegex()

private fun List<String>.part1() = reduce { one, two -> one + two }.let {
    mulRegex.findAll(it).sumOf { result ->
        val first = result.groupValues[1].toInt()
        val second = result.groupValues[2].toInt()
        first * second
    }
}

private fun List<String>.part2() = reduce { one, two -> one + two }.let { content ->
    val dontIndexes = "don't\\(\\)".toRegex().findAll(content).map { match -> match.range.first }
    val doIndexes = "do\\(\\)".toRegex().findAll(content).map { match -> match.range.first }
    val possibleIndexes = mutableListOf<Int>()
    var possible = true
    content.indices.forEach { index ->
        if(doIndexes.contains(index)) possible = true
        if(dontIndexes.contains(index)) possible = false
        if(possible) possibleIndexes.add(index)
    }
    mulRegex.findAll(content).sumOf { result ->
        if(possibleIndexes.contains(result.range.first).not()) return@sumOf 0
        val first = result.groupValues[1].toInt()
        val second = result.groupValues[2].toInt()
        first * second
    }
}