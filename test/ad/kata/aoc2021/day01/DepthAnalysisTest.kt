package ad.kata.aoc2021.day01

import ad.kata.aoc2021.toIntSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DepthAnalysisTest {

    @ParameterizedTest
    @CsvSource(
        "[]; 0",
        "[1]; 0",
        "[1, 2]; 1",
        "[1, 2, 1]; 1",
        "[1, 2, 1, 2]; 2",
        "[1, 2, 3, 1]; 2",
        "[1, 2, 3, 4]; 3",
        "[199, 200, 208, 210, 200, 207, 240, 269, 260, 263]; 7",
        delimiter = ';'
    )
    fun `tells the total number of times a depth measurement increases`(
        sweepReport: String,
        expectedTotalIncreases: Int
    ) {
        assertThat(
            DepthAnalysis(
                sweepReport.parseDepthsSequence()
            ).totalIncreases()
        ).isEqualTo(
            expectedTotalIncreases
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[]; 0",
        "[1]; 0",
        "[1, 2, 3]; 0",
        "[1, 2, 3, 4]; 1",
        "[1, 2, 3, 1]; 0",
        "[1, 2, 3, 2]; 1",
        "[1, 2, 3, 2, 4]; 2",
        "[199, 200, 208, 210, 200, 207, 240, 269, 260, 263]; 5",
        delimiter = ';'
    )
    fun `tells the number of depth measurement increases for a three-measurement sliding window`(
        sweepReport: String,
        expectedTotalIncreases: Int
    ) {
        assertThat(
            DepthAnalysis(
                sweepReport.parseDepthsSequence()
            ).totalIncreasesAfterSmoothing()
        ).isEqualTo(
            expectedTotalIncreases
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[1, 0]; 0",
        "[1, 1]; 0",
        "[1, 2]; 1",
        delimiter = ';'
    )
    fun `assume that depth increase must be strictly greater`(
        sweepReport: String,
        expectedTotalIncreases: Int
    ) {
        assertThat(
            DepthAnalysis(
                sweepReport.parseDepthsSequence()
            ).totalIncreases()
        ).isEqualTo(
            expectedTotalIncreases
        )
    }

    @Test
    fun `day01 puzzle number of total increases`() {
        assertThat(
            DepthAnalysis(
                sonarSweepReportFromInput("day01.input-sample")
            ).totalIncreases()
        ).isEqualTo(
            21
        )
    }

    @Test
    fun `day01 puzzle number of total increases after smoothing`() {
        assertThat(
            DepthAnalysis(
                sonarSweepReportFromInput("day01.input-sample")
            ).totalIncreasesAfterSmoothing()
        ).isEqualTo(
            18
        )
    }
}

private fun String.parseDepthsSequence() =
    toIntSequence().map { DepthMeasurement(it) }