package ad.kata.aoc2021.day03

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LiveSupportRatingTest {

    @ParameterizedTest
    @CsvSource(
        "[001,111,101]; 7",
        "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]; 230",
        delimiter = ';'
    )
    fun `live support rating is the product of oxygen generator rating and CO2 scrubber rating`(
        numbers: String,
        powerConsumption: Int
    ) {
        Assertions.assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).liveSupportRating()
        ).isEqualTo(
            powerConsumption
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[0]; 0",
        "[1]; 1",
        "[0,0]; 0",
        "[1,0]; 1",
        "[0,1]; 1",
        "[1,1]; 1",
        "[0,0,1]; 0",
        "[0,1,0]; 0",
        "[0,1,1]; 1",
        "[1,0,0]; 0",
        "[1,0,1]; 1",
        "[1,1,0]; 1",
        "[1,1,1]; 1",
        "[001,111,101]; 111",
        "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]; 10111",
        delimiter = ';'
    )
    fun `oxygen generator rating is determined by filtering down to 1 number via most common bits at position`(
        numbers: String,
        expectedGammaRate: String
    ) {
        Assertions.assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).oxygenGeneratorRating
        ).isEqualTo(
            BinaryNumber(expectedGammaRate)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["[]"])
    fun `oxygen generator rating is uncertain when no most common bit can be determined`(
        numbers: String
    ) {
        Assertions.assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).cO2ScrubberRating
        ).isNull()
    }

    @ParameterizedTest
    @CsvSource(
        "[0]; 0",
        "[1]; 1",
        "[1,0]; 0",
        "[0,1]; 0",
        "[0,0,1]; 1",
        "[0,1,0]; 1",
        "[0,1,1]; 0",
        "[1,0,0]; 1",
        "[1,0,1]; 0",
        "[1,1,0]; 0",
        "[001,111,101]; 001",
        "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]; 01010",
        delimiter = ';'
    )
    fun `CO2 scrubber rating is determined by filtering down to 1 number via least common bits at position`(
        numbers: String,
        expectedGammaRate: String
    ) {
        Assertions.assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).cO2ScrubberRating
        ).isEqualTo(
            BinaryNumber(expectedGammaRate)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["[]", "[0,0]", "[1,1]", "[0,0,0]", "[1,1,1]"])
    fun `CO2 scrubber rating is uncertain when no least common bit can be determined`(
        numbers: String
    ) {
        Assertions.assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).cO2ScrubberRating
        ).isNull()
    }
}