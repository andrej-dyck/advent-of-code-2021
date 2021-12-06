package ad.kata.aoc2021.day03

import ad.kata.aoc2021.parseList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PowerConsumptionTest {

    @ParameterizedTest
    @CsvSource(
        "[0]; 0",
        "[1]; 0",
        "[00,01]; 2",
        "[00,11]; 0",
        "[01,01]; 2",
        "[10,01]; 0",
        "[10,00]; 2",
        "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]; 198",
        delimiter = ';'
    )
    fun `power consumption is the product of gama and epsilon rate`(
        numbers: String,
        powerConsumption: Int
    ) {
        assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).powerConsumption()
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
        "[00,01]; 01",
        "[00,11]; 11",
        "[01,01]; 01",
        "[10,01]; 11",
        "[10,00]; 10",
        "[0,0,1]; 0",
        "[0,1,0]; 0",
        "[1,0,0]; 0",
        "[0,1,1]; 1",
        "[1,1,0]; 1",
        "[1,1,1]; 1",
        "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]; 10110",
        delimiter = ';'
    )
    fun `gamma rate is determined by the most common bit in the corresponding position of all numbers`(
        numbers: String,
        expectedGammaRate: String
    ) {
        assertThat(
            DiagnosticReport(numbers.parseBinaryNumbersList()).gammaRate
        ).isEqualTo(
            BinaryNumber(expectedGammaRate)
        )
    }

    @Test
    fun `gamma rate is 0 when no status codes are given`() {
        val emptyDiagnosticReport = DiagnosticReport(emptyList())

        assertThat(emptyDiagnosticReport.gammaRate.toInt()).isZero
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[0]",
            "[1]",
            "[00,01]",
            "[00,11]",
            "[01,01]",
            "[10,01]",
            "[10,00]",
            "[00100,11110,10110,10111,10101,01111,00111,11100,10000,11001,00010,01010]",
        ]
    )
    fun `epsilon rate is the binary inverse of gama rate`(numbers: String) {
        val diagnosticReport = DiagnosticReport(numbers.parseBinaryNumbersList())

        assertThat(
            diagnosticReport.epsilonRate
        ).isEqualTo(
            diagnosticReport.gammaRate.inv()
        )
    }

    @Test
    fun `reads diagnostic report from input`() {
        assertThat(
            diagnosticReportFromInput("day03.input-sample").statusCodes
        ).containsExactly(
            BinaryNumber("00100"),
            BinaryNumber("11110"),
            BinaryNumber("10110"),
            BinaryNumber("10111"),
            BinaryNumber("10101"),
            BinaryNumber("01111"),
            BinaryNumber("00111"),
            BinaryNumber("11100"),
            BinaryNumber("10000"),
            BinaryNumber("11001"),
            BinaryNumber("00010"),
            BinaryNumber("01010"),
        )
    }
}

internal fun String.parseBinaryNumbersList() = parseList { BinaryNumber(it) }