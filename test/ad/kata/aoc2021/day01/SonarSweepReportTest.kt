package ad.kata.aoc2021.day01

import ad.kata.aoc2021.assertThatSeq
import org.junit.jupiter.api.Test

class SonarSweepReportTest {

    @Test
    fun `reads sonar sweep report from file`() {
        assertThatSeq(
            sonarSweepReportFromInput("day01.input-sample").depths
        ).startsWith(
            DepthMeasurement(187),
            DepthMeasurement(195),
            DepthMeasurement(199),
            DepthMeasurement(218),
            DepthMeasurement(221)
        ).hasSize(25)
    }
}