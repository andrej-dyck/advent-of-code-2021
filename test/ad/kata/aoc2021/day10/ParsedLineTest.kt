package ad.kata.aoc2021.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ParsedLineTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "()",
            "[]",
            "{}",
            "<>",
            "()[]",
            "[]{}",
            "{}<>",
            "<>()",
            "([])",
            "[{}]",
            "{<>}",
            "<()>",
            "{()()()}",
            "<([{}])>",
            "[<>({}){}[([])<>]]",
            "(((((((((())))))))))",
        ]
    )
    fun `valid lines are a sequence brackets where each opening bracket has a closing one at correct nesting depth`(
        line: String
    ) {
        assertThat(
            NavigationLine(line).parse()
        ).isEqualTo(
            validLineOf(line)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "(]; ]",
        "{()()()>; >",
        "(((()))}; }",
        "<([]){()}[{}]); )",
        "{([(<{}[<>[]}>{[]{[(<()>; }",
        "[[<[([]))<([[{}[[()]]]; )",
        "[{[{({}]{}}([{[{{{}}([]; ]",
        "[<(<(<(<{}))><([]([](); )",
        "<{([([[(<>()){}]>(<<{{; >",
        delimiter = ';'
    )
    fun `corrupt lines are lines that have an unexpected closing bracket`(
        line: String,
        unexpectedBracket: Char
    ) {
        assertThat(
            NavigationLine(line).parse()
        ).isEqualTo(
            corruptLineOf(line, unexpectedBracket)
        )
    }
}

internal fun validLineOf(line: String) = ValidLine(NavigationLine(line))
internal fun corruptLineOf(line: String, unexpectedBracket: Char) =
    CorruptLine(NavigationLine(line), unexpectedBracket)