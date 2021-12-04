package ad.kata.aoc2021.day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BinaryNumberTest {

    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "1, 1",
        "01, 1",
        "10, 2",
        "11, 3",
        "01001, 9",
        "10110, 22",
    )
    fun `binary numbers are represented by 0s and 1s`(
        binaryNumber: String,
        expectedIntValue: Int
    ) {
        assertThat(
            BinaryNumber(binaryNumber).toInt()
        ).isEqualTo(expectedIntValue)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "1, 0",
        "10110, 01001",
    )
    fun `binary number can be inverted`(
        binaryNumber: String,
        expectedInvNumber: String
    ) {
        assertThat(
            BinaryNumber(binaryNumber).inv()
        ).isEqualTo(
            BinaryNumber(expectedInvNumber)
        )
    }

    @Test
    fun `binary number can't be empty`() {
        assertThrows<IllegalArgumentException> { BinaryNumber("") }
    }

    @Test
    fun `binary number can't have anything else then 0s and 1s`() {
        assertThrows<IllegalArgumentException> { BinaryNumber("201") }
    }
}