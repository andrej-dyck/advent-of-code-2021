package ad.kata.aoc2021.day12

import ad.kata.aoc2021.parseList
import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CaveSystemTest {

    @ParameterizedTest
    @CsvSource(
        "start-a-end; [start,a,end]",
        "start-a,a-end; [start,a,end]",
        "a-start,end-a; [start,a,end]",
        "start-a-end,start-b-end; [start,a,end]^[start,b,end]",
        "start-a-end,start-b-a; [start,a,end]^[start,b,a,end]",
        delimiter = ';'
    )
    fun `finds distinct paths through the cave system`(
        caveSystem: String,
        expectedDistinctPaths: String
    ) {
        assertThat(
            caveSystemOf(caveSystem).distinctPaths()
        ).containsExactlyInAnyOrderElementsOf(
            expectedDistinctPaths.parseDistinctPaths()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "start-A-end,A-c; [start,A,end]^[start,A,c,A,end]",
        "start-A-end,start-b-end,A-b; [start,A,end]^[start,A,b,A,end]^[start,A,b,end]^[start,b,end]^[start,b,A,end]",
        delimiter = ';'
    )
    fun `paths can visit large caves multiple times`(
        caveSystem: String,
        expectedDistinctPaths: String
    ) {
        assertThat(
            caveSystemOf(caveSystem).distinctPaths()
        ).containsExactlyInAnyOrderElementsOf(
            expectedDistinctPaths.parseDistinctPaths()
        )
    }

    @Suppress("MaxLineLength")
    @ParameterizedTest
    @CsvSource(
        "start-A-end,start-b-end,A-b,A-c,b-d; 10",
        "dc-end,HN-start,start-kj,dc-start,dc-HN,LN-dc,HN-end,kj-sa,kj-HN,kj-dc; 19",
        "fs-end,he-DX,fs-he,start-DX,pj-DX,end-zg,zg-sl,zg-pj,pj-he,RW-he,fs-DX,pj-RW,zg-RW,start-pj,he-WI,zg-he,pj-fs,start-RW; 226",
        delimiter = ';'
    )
    fun `finds all distinct paths through the cave system where large caves can be visited multiple times`(
        caveSystem: String,
        expectedNumberOfDistinctPaths: Int
    ) {
        assertThat(
            caveSystemOf(caveSystem).distinctPaths()
        ).hasSize(
            expectedNumberOfDistinctPaths
        )
    }

    @Test
    fun `reads cave system from input`() {
        assertThat(
            caveSystemFromInput("day12.input-sample")
        ).isEqualTo(
            caveSystemOf(
                "start" to "A",
                "start" to "b",
                "A" to "c",
                "A" to "b",
                "b" to "d",
                "A" to "end",
                "b" to "end",
            )
        )
    }
}

private fun caveSystemOf(vararg connections: Edge<String>) =
    CaveSystem(unidirectionalGraphOf(connections.map { (a, b) -> Cave(a) to Cave(b) }))

private fun caveSystemOf(csvEdges: String) =
    CaveSystem(unidirectionalGraphOf(csvEdges.split(',')) { Cave(it) })

private fun String.parseDistinctPaths() =
    parseSequence('^') { it.parseList { c -> Cave(c) } }.toList()