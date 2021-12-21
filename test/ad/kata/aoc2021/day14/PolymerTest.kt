package ad.kata.aoc2021.day14

import ad.kata.aoc2021.extensions.splitTrim
import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PolymerTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "N"])
    fun `polymer templates of size 0 or 1 are not allowed`(template: String) {
        assertThrows<IllegalArgumentException> {
            Polymer(template)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "NC, 2",
        "NCB, 3",
        "NN, 2",
        "NNNNN, 5",
        "NNCB, 4",
        "NCNBCHB, 7",
        "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB, 49",
    )
    fun `can tell the length of the polymer`(template: String, expectedLength: Long) {
        assertThat(
            Polymer(template).length()
        ).isEqualTo(
            expectedLength
        )
    }

    @ParameterizedTest
    @CsvSource(
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
        expectedDifference: Long
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
        .associate { (e, c) -> Element(e.first()) to c.toLong() }