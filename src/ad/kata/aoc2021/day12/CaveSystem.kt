package ad.kata.aoc2021.day12

import ad.kata.aoc2021.PuzzleInput

data class CaveSystem(private val connections: Graph<Cave>) {

    fun distinctPaths(): Paths<Cave> =
        connections.distinctPaths(start, end, canRevisit = Cave::isLargeCave)

    companion object {
        private val start = Cave("start")
        private val end = Cave("end")
    }
}

@JvmInline
value class Cave(private val name: String) {

    fun isLargeCave() = name.all { it.isUpperCase() }

    override fun toString() = "Cave('$name')"
}

fun caveSystemFromInput(filename: String) = CaveSystem(
    unidirectionalGraphOf(PuzzleInput(filename).lines().toList()) { Cave(it) }
)
