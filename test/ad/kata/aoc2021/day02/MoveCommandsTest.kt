package ad.kata.aoc2021.day02

import ad.kata.aoc2021.parseIntTriple
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoveCommandsTest {

    @ParameterizedTest
    @CsvSource(
        "down 0; (0,0,0); (0,0,0)",
        "down 0; (2,3,7); (2,3,7)",
        "down 1; (0,0,0); (0,0,1)",
        "down 1; (0,0,1); (0,0,2)",
        "down 1; (0,0,-1); (0,0,0)",
        "down 2; (0,0,5); (0,0,7)",
        "down 5; (0,0,3); (0,0,8)",
        delimiter = ';'
    )
    fun `down tilts the submarine downwards (increases aim)`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseSubmarinePosition()
            )
        ).isEqualTo(
            expectedDestination.parseSubmarinePosition()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "up 0; (0,0,0); (0,0,0)",
        "up 0; (2,3,7); (2,3,7)",
        "up 1; (0,0,0); (0,0,-1)",
        "up 1; (0,0,1); (0,0,0)",
        "up 1; (0,0,-1); (0,0,-2)",
        "up 5; (0,0,0); (0,0,-5)",
        "up 2; (0,0,5); (0,0,3)",
        delimiter = ';'
    )
    fun `up tilts the submarine upwards (decreases aim)`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseSubmarinePosition()
            )
        ).isEqualTo(
            expectedDestination.parseSubmarinePosition()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "forward 0; (0,0,0); (0,0,0)",
        "forward 0; (5,7,2); (5,7,2)",
        "forward 1; (0,0,0); (1,0,0)",
        "forward 1; (1,0,0); (2,0,0)",
        "forward 1; (-1,0,0); (0,0,0)",
        "forward 5; (0,0,0); (5,0,0)",
        "forward 2; (5,0,0); (7,0,0)",
        "forward 7; (5,5,0); (12,5,0)",
        delimiter = ';'
    )
    fun `forward increases the horizontal position`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseSubmarinePosition()
            )
        ).isEqualTo(
            expectedDestination.parseSubmarinePosition()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "forward 1; (0,0,1); (1,1,1)",
        "forward 1; (0,0,-1); (1,-1,-1)",
        "forward 1; (1,0,1); (2,1,1)",
        "forward 2; (1,0,5); (3,10,5)",
        delimiter = ';'
    )
    fun `forward increases depth by (aim x units)`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseSubmarinePosition()
            )
        ).isEqualTo(
            expectedDestination.parseSubmarinePosition()
        )
    }
}

internal fun String.parseSubmarinePosition() =
    parseIntTriple().let { (h, d, a) -> SubmarinePosition(horizontalPosition = h, depth = d, aim = a) }