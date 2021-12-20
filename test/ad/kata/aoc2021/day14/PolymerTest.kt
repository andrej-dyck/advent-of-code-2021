package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.splitTrim
import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PolymerTest {

    @ParameterizedTest
    @CsvSource(
        "N, N:1",
        "NN, N:2",
        "NNNNN, N:5",
        "NNCB, N:2^B:1^C:1",
        "NCNBCHB, N:2^B:2^C:2^H:1",
        "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB, N:11^B:23^C:10^H:5",
    )
    fun `can count elements`(template: String, expectedElementCount: String) {
        assertThat(
            Polymer(template).elementCount()
        ).isEqualTo(
            expectedElementCount.parseCountMap()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "N, 0",
        "NN, 0",
        "NB, 0",
        "NBB, 1",
        "NNB, 1",
        "NNNB, 2",
        "NNNBCC, 2",
        "NNNBCCCC, 3",
        "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB, 18",
    )
    fun `can tell the difference between most and least common element`(
        template: String,
        expectedDifference: Int
    ) {
        assertThat(
            Polymer(template).differenceOfMostAndLeastCommonElement()
        ).isEqualTo(
            expectedDifference
        )
    }
}

private fun String.parseCountMap() =
    parseSequence { it.splitTrim(":") }
        .associate { (e, c) -> Element(e.first()) to c.toInt() }