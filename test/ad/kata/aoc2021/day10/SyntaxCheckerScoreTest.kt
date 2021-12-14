package ad.kata.aoc2021.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SyntaxCheckerScoreTest {

    @ParameterizedTest
    @CsvSource(
        "[), 3",
        "(], 57",
        "(}, 1197",
        "(>, 25137",
    )
    fun `has following syntax error score for unexpected chars in corrupt lines`(
        corruptLine: String,
        expectedScore: Long
    ) {
        assertThat(
            syntaxCheckerScoreOf(corruptLine).total()
        ).isEqualTo(
            Score(expectedScore)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[)^[), 6",
        "(]^[), 60",
        "(]^(], 114",
        "(}^[), 1200",
        "(}^(], 1254",
        "(}^(}, 2394",
        "(>^[), 25140",
        "(>^(], 25194",
        "(>^(}, 26334",
        "(>^(>, 50274",
    )
    fun `adds scores for each line`(corruptLines: String, expectedScore: Long) {
        assertThat(
            syntaxCheckerScoreOf(
                *corruptLines.split('^').toTypedArray()
            ).total()
        ).isEqualTo(
            Score(expectedScore)
        )
    }

    @Test
    fun `score is 0 for valid lines`() {
        assertThat(syntaxCheckerScoreOf("()").total())
            .isEqualTo(Score.Zero)
    }

    @Test
    fun `score is 0 for incomplete lines`() {
        assertThat(syntaxCheckerScoreOf("(").total())
            .isEqualTo(Score.Zero)
    }

    @Test
    fun `corrupt lines of sample input have a total sore of 26397`() {
        assertThat(
            syntaxCheckerScoreOf(
                "{([(<{}[<>[]}>{[]{[(<()>",
                "[[<[([]))<([[{}[[()]]]",
                "[{[{({}]{}}([{[{{{}}([]",
                "[<(<(<(<{}))><([]([]()",
                "<{([([[(<>()){}]>(<<{{"
            ).total()
        ).isEqualTo(
            Score(points = 26397)
        )
    }
}

internal fun syntaxCheckerScoreOf(vararg lines: String) =
    SyntaxCheckerScore(NavigationProgram(*lines))