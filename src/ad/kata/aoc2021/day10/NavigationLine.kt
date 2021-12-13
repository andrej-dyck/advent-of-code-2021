package ad.kata.aoc2021.day10

@JvmInline
value class NavigationLine(private val line: String) {

    init {
        line.all { it in bracketPairs.keys || it in bracketPairs.values }
    }

    fun hasIndex(index: Int) = index in line.indices
    fun charAt(index: Int) = line[index]

    companion object {
        internal val bracketPairs = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    }
}
