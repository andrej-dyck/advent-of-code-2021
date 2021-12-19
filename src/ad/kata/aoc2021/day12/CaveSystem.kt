package ad.kata.aoc2021.day12

import ad.kata.aoc2021.PuzzleInput

data class CaveSystem(private val connections: Graph<Cave>) {

    fun findPaths(): Paths<Cave> =
        connections.distinctPaths(start, end, canRevisit = { c, _ -> c.isLargeCave() })

    fun findPathsWithSlightlyMoreTime(): Paths<Cave> =
        connections.distinctPaths(start, end, canRevisit = { c, p ->
            c.isLargeCave() || p.noSmallCaveVisitedTwice()
        })

    private fun Path<Cave>.noSmallCaveVisitedTwice() =
        filter(Cave::isSmallCave).groupingBy { it }.eachCount().none { it.value > 1 }

    companion object {
        private val start = Cave("start")
        private val end = Cave("end")
    }
}

@JvmInline
value class Cave(private val name: String) {

    fun isLargeCave() = name.all { it.isUpperCase() }
    fun isSmallCave() = !isLargeCave()

    override fun toString() = "Cave('$name')"
}

fun caveSystemFromInput(filename: String) =
    CaveSystem(unidirectionalGraphOf(PuzzleInput(filename).lines().toList()) { Cave(it) })
