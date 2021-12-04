package ad.kata.aoc2021.extensions

import ad.kata.aoc2021.parseSequence
import ad.kata.aoc2021.parseStringList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class TransposeTest {

    @ParameterizedTest
    @CsvSource(
        "[1] -> [1]",
        "[1]^[2] -> [1,2]",
        "[a,b]^[1,2] -> [a,1]^[b,2]",
        "[a,b,c,d,e]^[1,2,3,4,5] -> [a,1]^[b,2]^[c,3]^[d,4]^[e,5]",
        "[a,b]^[1,2]^[!,?] -> [a,1,!]^[b,2,?]",
        "[a,b,c]^[1,2,3]^[x,y,z]^[!,?,.] -> [a,1,x,!]^[b,2,y,?]^[c,3,z,.]",
        delimiterString = "->"
    )
    fun `transposes rows into columns and vice versa`(
        matrix: String,
        expectedMatrix: String
    ) {
        assertThat(
            matrix.parseSequenceOfStringLists().transposed().map { it.toList() }
        ).isEqualTo(
            expectedMatrix.parseSequenceOfStringLists().toList()
        )
    }

    @Test
    fun `is empty when inner list is empty`() {
        assertThat(
            sequenceOf(emptyList<Any>()).transposed()
        ).isEmpty()
    }

    @Test
    fun `is empty list is empty`() {
        assertThat(
            emptySequence<List<Any>>().transposed()
        ).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[1,2]^[1]",
            "[1,2,3]^[1]^[1,2,3]",
            "[1,2,3]^[1,2]^[1,2,3]",
            "[1,2,3]^[1,2,3]^[1]",
            "[1,2,3]^[1,2,3]^[1,2]",
        ]
    )
    fun `throws when rows which are consumed are shorter than the first`(differentSizedRows: String) {
        assertThrows<IndexOutOfBoundsException> {
            differentSizedRows
                .parseSequenceOfStringLists()
                .transposed()
                .map { it.toList() }
        }
    }

    private fun String.parseSequenceOfStringLists() =
        parseSequence { it.parseStringList() }
}