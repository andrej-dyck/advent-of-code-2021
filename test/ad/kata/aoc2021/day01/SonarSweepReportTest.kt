package ad.kata.aoc2021.day01

import ad.kata.aoc2021.assertThatSeq
import org.junit.jupiter.api.Test

class SonarSweepReportTest {

    @Test
    fun `reads sonar sweep report from file`() {
        assertThatSeq(
            sonarSweepReportFromInput("day01.input-sample").depths
        ).containsExactly(
            DepthMeasurement(199),
            DepthMeasurement(200),
            DepthMeasurement(208),
            DepthMeasurement(210),
            DepthMeasurement(200),
            DepthMeasurement(207),
            DepthMeasurement(240),
            DepthMeasurement(269),
            DepthMeasurement(260),
            DepthMeasurement(263)
        )
    }
}