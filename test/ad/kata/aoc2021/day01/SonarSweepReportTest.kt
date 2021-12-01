package ad.kata.aoc2021.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SonarSweepReportTest {

    @Test
    fun `reads sonar sweep report from file`() {
        assertThat(
            sonarSweepReportFromInput("day01.test-input").depths
        ).startsWith(
            187,
            195,
            199,
            218,
            221
        ).hasSize(25)
    }
}