package ad.kata.aoc2021.day02

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SubmarineCourseTest {

    @ParameterizedTest
    @CsvSource(
        "[]; (0,0,0)",
        "[down 5]; (0,0,5)",
        "[forward 5]; (5,0,0)",
        "[up 5]; (0,0,-5)",
        "[forward 7, down 5, up 2]; (7,0,3)",
        "[down 5, up 2, forward 7]; (7,21,3)",
        "[forward 5, down 5, forward 8, up 3, down 8, forward 2]; (15,60,10)",
        delimiter = ';'
    )
    fun `determines final destination with given planned course`(
        plannedCourse: String,
        expectedDestination: String
    ) {
        assertThat(
            SubmarineCourse(
                plannedCourse.parseSequence { it.parseMoveCommand() }
            ).finalDestination()
        ).isEqualTo(
            expectedDestination.parseSubmarinePosition()
        )
    }

    @Test
    fun `day02 puzzle destination after course`() {
        assertThat(
            submarineCourseFromInput("day02.input-sample").finalDestination()
        ).isEqualTo(
            SubmarinePosition(horizontalPosition = 46, depth = 727, aim = 16)
        )
    }

    @Test
    fun `reads submarine course from file`() {
        assertThatSeq(
            submarineCourseFromInput("day02.input-sample").commands
        ).startsWith(
            Forward(8),
            Down(9),
            Up(1),
            Forward(2),
            Down(6)
        ).hasSize(25)
    }
}