package ad.kata.aoc2021.day02

import ad.kata.aoc2021.parseIntPair
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoveCommandsTest {

    @ParameterizedTest
    @CsvSource(
        "forward 0; (0,0); (0,0)",
        "forward 1; (0,0); (1,0)",
        "forward 1; (1,0); (2,0)",
        "forward 1; (-1,0); (0,0)",
        "forward 0; (5,7); (5,7)",
        "forward 5; (0,0); (5,0)",
        "forward 2; (5,0); (7,0)",
        delimiter = ';'
    )
    fun `forward adds movement only in horizontal direction`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseMoveVector()
            )
        ).isEqualTo(
            expectedDestination.parseMoveVector()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "up 0; (0,0); (0,0)",
        "up 1; (0,0); (0,-1)",
        "up 1; (0,1); (0,0)",
        "up 1; (0,-1); (0,-2)",
        "up 0; (5,7); (5,7)",
        "up 5; (0,0); (0,-5)",
        "up 2; (0,5); (0,3)",
        delimiter = ';'
    )
    fun `up adds movement only in upwards depth direction`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseMoveVector()
            )
        ).isEqualTo(
            expectedDestination.parseMoveVector()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "down 0; (0,0); (0,0)",
        "down 1; (0,0); (0,1)",
        "down 1; (0,1); (0,2)",
        "down 1; (0,-1); (0,0)",
        "down 0; (5,7); (5,7)",
        "down 5; (0,0); (0,5)",
        "down 2; (0,5); (0,7)",
        delimiter = ';'
    )
    fun `down adds movement only in downwards depth direction`(
        moveCommand: String,
        origin: String,
        expectedDestination: String
    ) {
        assertThat(
            moveCommand.parseMoveCommand().move(
                origin.parseMoveVector()
            )
        ).isEqualTo(
            expectedDestination.parseMoveVector()
        )
    }
}

internal fun String.parseMoveVector() =
    parseIntPair().let { MoveVector(horizontalPosition = it.first, depth = it.second) }