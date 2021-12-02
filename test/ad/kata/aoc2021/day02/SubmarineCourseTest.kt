package ad.kata.aoc2021.day02

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.parseList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SubmarineCourseTest {

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

    @ParameterizedTest
    @CsvSource(
        "[]; (0,0)",
        "[down 5]; (0,5)",
        "[forward 5]; (5,0)",
        "[up 5]; (0,-5)",
        "[forward 7, down 5, up 2]; (7,3)",
        "[forward 5, down 5, forward 8, up 3, down 8, forward 2]; (15,10)",
        delimiter = ';'
    )
    fun `determines final destination with given planned course`(
        plannedCourse: String,
        expectedDestination: String
    ) {
        assertThat(
            SubmarineCourse(
                plannedCourse.parseList { it.parseMoveCommand() }.asSequence()
            ).finalDestination()
        ).isEqualTo(
            expectedDestination.parseMoveVector()
        )
    }

    @Test
    fun `day02 puzzle destination after course`() {
        assertThat(
            submarineCourseFromInput("day02.input-sample").finalDestination()
        ).isEqualTo(
            MoveVector(horizontalPosition = 46, depth = 16)
        )
    }
}