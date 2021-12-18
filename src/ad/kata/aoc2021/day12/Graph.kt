package ad.kata.aoc2021.day12

import ad.kata.aoc2021.extensions.toMapMerging

data class Graph<T>(private val edges: Map<T, Set<T>>) {

    fun distinctPaths(from: T, to: T, canRevisit: (T) -> Boolean = { false }) =
        findPaths(from, to, canRevisit)

    private fun findPaths(
        from: T,
        to: T,
        canRevisit: (T) -> Boolean,
        path: Path<T> = listOf(from)
    ): Paths<T> {
        if (from == to) return setOf(path)

        val neighbors = adjacentNodesOf(from) - path.filterNot(canRevisit).toSet()
        return neighbors.flatMap { n -> findPaths(n, to, canRevisit, path + n) }.toHashSet()
    }

    private fun adjacentNodesOf(node: T) = edges[node] ?: emptySet()
}

typealias Edge<T> = Pair<T, T>
typealias Path<T> = List<T>
typealias Paths<T> = Set<Path<T>>

fun <T> unidirectionalGraphOf(edges: List<Edge<T>>) =
    Graph((edges + edges.reversedEdges()).toMapMerging { it.toHashSet() })

private fun <T> List<Edge<T>>.reversedEdges() = map { (a, b) -> b to a }

inline fun <T> unidirectionalGraphOf(
    edges: List<String>,
    delimiter: Char = '-',
    transform: (String) -> T
) =
    unidirectionalGraphOf(
        edges.flatMap {
            it.split(delimiter)
                .map(transform)
                .windowed(2) { (a, b) -> a to b }
        }
    )
