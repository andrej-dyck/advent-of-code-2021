package ad.kata.aoc2021.day12

import ad.kata.aoc2021.parsePair
import ad.kata.aoc2021.parseSequence
import ad.kata.aoc2021.parseStringList
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GraphTest {

    @ParameterizedTest
    @CsvSource(
        "a; a,a; [a]",
        "a-z; a,a; [a]",
        "a-z; a,z; [a,z]",
        "a-b-z,a-c-z; a,z; [a,b,z]^[a,c,z]",
        "a-b-z,a-c-z,b-c; a,z; [a,b,z]^[a,c,z]^[a,b,c,z]^[a,c,b,z]",
        "a-b-z,b-c; a,z; [a,b,z]",
        delimiter = ';'
    )
    fun `finds all distinct paths from start to finish`(
        graphEdges: String,
        fromTo: String,
        expectedPaths: String
    ) {
        Assertions.assertThat(
            graphOf(graphEdges).distinctPaths(fromTo.parsePair())
        ).containsExactlyInAnyOrderElementsOf(
            expectedPaths.parseDistinctPaths()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "a-b-z,b-c; a,z; b; [a,b,z]^[a,b,c,b,z]",
        "a-b-z,a-c-z,b-c; a,z; b; [a,b,z]^[a,c,z]^[a,b,c,z]^[a,c,b,z]^[a,b,c,b,z]",
        delimiter = ';'
    )
    fun `can revisit nodes on paths`(
        graphEdges: String,
        fromTo: String,
        nodeThatCanBeRevisited: String,
        expectedPaths: String
    ) {
        Assertions.assertThat(
            graphOf(graphEdges).distinctPaths(fromTo.parsePair(), canVisitAgain = { n, _ ->
                n == nodeThatCanBeRevisited
            })
        ).containsExactlyInAnyOrderElementsOf(
            expectedPaths.parseDistinctPaths()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "a-b-z,a-c-z,b-c; a,z; a; [a,b,z]^[a,c,z]^[a,b,c,z]^[a,c,b,z]",
        "a-b-z,a-c-z,b-c; a,z; z; [a,b,z]^[a,c,z]^[a,b,c,z]^[a,c,b,z]",
        delimiter = ';'
    )
    fun `cannot revisit start or end node`(
        graphEdges: String,
        fromTo: String,
        nodeThatCanBeRevisited: String,
        expectedPaths: String
    ) {
        Assertions.assertThat(
            graphOf(graphEdges).distinctPaths(fromTo.parsePair(), canVisitAgain = { n, _ ->
                n == nodeThatCanBeRevisited
            })
        ).containsExactlyInAnyOrderElementsOf(
            expectedPaths.parseDistinctPaths()
        )
    }
}

private fun graphOf(csvEdges: String) =
    unidirectionalGraphOf(csvEdges.split(',')) { it }

private fun <T> Graph<T>.distinctPaths(
    fromTo: Pair<T, T>,
    canVisitAgain: (T, Path<T>) -> Boolean = { _, _ -> false }
) =
    distinctPaths(fromTo.first, fromTo.second, canVisitAgain)

private fun String.parseDistinctPaths() =
    parseSequence('^') { it.parseStringList() }.toList()