package ad.kata.aoc2021.extensions

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.toListOfLists
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CsvLinesTest {

    @ParameterizedTest
    @CsvSource(
        "''; []",
        "^; []",
        "a^b; [[a]^[b]]",
        "a^^b; [[a]^[b]]",
        "a^b^c; [[a]^[b]^[c]]",
        "a,b,c^d,e,f; [[a,b,c]^[d,e,f]]",
        "a,b,c^d,e,f^g,h; [[a,b,c]^[d,e,f]^[g,h]]",
        delimiter = ';'
    )
    fun `splits each non-empty line into columns delimited by delimiter and trims the values`(
        lines: String,
        expectedCsv: String
    ) {
        assertThatSeq(
            lines.splitToSequence('^').csvLines()
        ).containsExactlyElementsOf(
            expectedCsv.toListOfLists(outerDelimiter = '^')
        )
    }

    @ParameterizedTest
    @CsvSource(
        "a,b,c; []",
        "a,b,c^1,2,3; [[a=1,b=2,c=3]]",
        "a,b,c^1,2,3^4,5,6; [[a=1,b=2,c=3]^[a=4,b=5,c=6]]",
        "a,b,c^1,2,3^4,5; [[a=1,b=2,c=3]^[a=4,b=5]]",
        delimiter = ';'
    )
    fun `csv lines with header takes first row as header`(
        lines: String,
        expectedCsv: String
    ) {
        assertThatSeq(
            lines.splitToSequence('^')
                .csvLinesWithHeaders()
                .map { m -> m.entries.map { e -> "${e.key}=${e.value}" } }
        ).containsExactlyElementsOf(
            expectedCsv.toListOfLists(outerDelimiter = '^')
        )
    }
}