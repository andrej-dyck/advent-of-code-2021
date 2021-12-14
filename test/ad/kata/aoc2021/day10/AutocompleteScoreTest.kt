package ad.kata.aoc2021.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AutocompleteScoreTest {

    @ParameterizedTest
    @CsvSource(
        "(, 1", // missing )
        "[, 2", // missing ]
        "{, 3", // missing }
        "<, 4", // missing >
    )
    fun `has following autocomplete score for missing chars in incomplete lines`(
        incompleteLine: String,
        expectedScore: Long
    ) {
        assertThat(
            autocompleteScoreOf(incompleteLine).scores
        ).containsExactly(
            Score(expectedScore)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "((, 6", // missing ))
        "[(, 7", // missing )]
        "{(, 8", // missing )}
        "<(, 9", // missing )>
        "([, 11", // missing ])
        "[[, 12", // missing ]]
        "{[, 13", // missing ]}
        "<[, 14", // missing ]>
        "({, 16", // missing })
        "[{, 17", // missing }]
        "{{, 18", // missing }}
        "<{, 19", // missing }>
        "(<, 21", // missing >)
        "[<, 22", // missing >]
        "{<, 23", // missing >}
        "<<, 24", // missing >>
        "[({(<(())[]>[[{[]{<()<>>, 288957", // missing }}]])})]
        "[(()[<>])]({[<{<<[]>>(, 5566", // missing )}>]})
        "(((({<>}<{<{<>}{[]{[]{}, 1480781", // missing }}>}>))))
        "{<[[]]>}<{[{[{[]{()[[[], 995444", // missing ]]}}]}]}>
        "<{([{{}}[<[[[<>{}]]]>[]], 294", // missing ])}>
    )
    fun `the score of an incomplete line is the missing bracket score plus the intermediate total times 5`(
        incompleteLine: String,
        expectedScore: Long
    ) {
        assertThat(
            autocompleteScoreOf(incompleteLine).scores
        ).containsExactly(
            Score(expectedScore)
        )
    }

    @Test
    fun `does not award score points for valid lines`() {
        assertThat(autocompleteScoreOf("()").scores).isEmpty()
    }

    @Test
    fun `does not award score points corrupted lines`() {
        assertThat(autocompleteScoreOf("(>").scores).isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "(, 1",
        "[, 2",
        "{, 3",
        "<, 4",
        "<^(^[, 2",
        "<^(^{, 3",
        "<^(^(, 1",
        "<^(^<, 4",
        "(<^<^(, 4",
        "(<^<^(, 4",
        "[({(<(())[]>[[{[]{<()<>>^[(()[<>])]({[<{<<[]>>(^(((({<>}<{<{<>}{[]{[]{}, 288957",
        "[(()[<>])]({[<{<<[]>>(^{<[[]]>}<{[{[{[]{()[[[]^<{([{{}}[<[[[<>{}]]]>[]], 5566",
    )
    fun `winning autocomplete score is the middle score`(
        incompleteLines: String,
        expectedWinnerScore: Long
    ) {
        assertThat(
            autocompleteScoreOf(
                *incompleteLines.split('^').toTypedArray()
            ).winner()
        ).isEqualTo(
            Score(expectedWinnerScore)
        )
    }

    @Test
    fun `winning autocomplete score for the sample data is 288957`() {
        assertThat(
            autocompleteScoreOf(
                "[({(<(())[]>[[{[]{<()<>>",
                "[(()[<>])]({[<{<<[]>>(",
                "(((({<>}<{<{<>}{[]{[]{}",
                "{<[[]]>}<{[{[{[]{()[[[]",
                "<{([{{}}[<[[[<>{}]]]>[]]"
            ).winner()
        ).isEqualTo(
            Score(points = 288_957)
        )
    }
}

internal fun autocompleteScoreOf(vararg lines: String) =
    AutocompleteScore(NavigationProgram(*lines))