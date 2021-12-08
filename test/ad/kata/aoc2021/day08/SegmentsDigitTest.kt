package ad.kata.aoc2021.day08

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class SegmentsDigitTest {

    @ParameterizedTest
    @ValueSource(strings = ["a", "b", "c", "d", "e", "f", "g", "cf", "acf", "abcdefg"])
    fun `accepts set of segments a,b,c,d,e,f,g`(segments: String) {
        assertThatCode {
            SegmentsDigit(segments)
        }.doesNotThrowAnyException()
    }

    @ParameterizedTest
    @CsvSource(
        "0, abcefg",
        "1, cf",
        "2, acdeg",
        "3, acdfg",
        "4, bcdf",
        "5, abdfg",
        "6, abdefg",
        "7, acf",
        "8, abcdefg",
        "9, abcdfg",
    )
    fun `accepts a digit to create segments`(digit: Int, segments: String) {
        assertThat(
            SegmentsDigit(Digit(digit))
        ).isEqualTo(
            SegmentsDigit(segments)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["h", "A", "abJ"])
    fun `throws when segments contain something other than a,b,c,d,e,f,g`(segments: String) {
        assertThrows<IllegalArgumentException> {
            SegmentsDigit(segments)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["aa", "aba", "acc"])
    fun `throws when segments contain duplicates`(segments: String) {
        assertThrows<IllegalArgumentException> {
            SegmentsDigit(segments)
        }
    }
}