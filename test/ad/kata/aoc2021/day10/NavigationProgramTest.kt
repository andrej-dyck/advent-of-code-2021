package ad.kata.aoc2021.day10

import ad.kata.aoc2021.assertThatSeq
import org.junit.jupiter.api.Test

class NavigationProgramTest {

    @Test
    fun `a correct program has no corrupt lines`() {
        assertThatSeq(
            NavigationProgram(
                "()",
                "[]",
                "([])",
                "{()()()}",
                "<([{}])>",
                "[<>({}){}[([])<>]]",
                "(((((((((())))))))))"
            ).corruptedLines()
        ).isEmpty()
    }

    @Test
    fun `a program identifies corrupt lines where a chunk closes with the wrong character`() {
        assertThatSeq(
            NavigationProgram(
                "()",
                "(]", // corrupt
                "[]",
                "([])",
                "{()()()}",
                "{()()()>", // corrupt
                "<([{}])>",
                "[<>({}){}[([])<>]]",
                "<([]){()}[{}])", // corrupt
                "(((((((((())))))))))",
                "(((()))}" // corrupt
            ).corruptedLines()
        ).containsExactly(
            corruptLineOf("(]", unexpectedBracket = ']'),
            corruptLineOf("{()()()>", unexpectedBracket = '>'),
            corruptLineOf("<([]){()}[{}])", unexpectedBracket = ')'),
            corruptLineOf("(((()))}", unexpectedBracket = '}'),
        )
    }

    @Test
    fun `reads navigation program from file`() {
        assertThatSeq(
            navigationProgramFromInput("day10.input-sample").lines
        ).containsExactly(
            NavigationLine("[({(<(())[]>[[{[]{<()<>>"),
            NavigationLine("[(()[<>])]({[<{<<[]>>("),
            NavigationLine("{([(<{}[<>[]}>{[]{[(<()>"),
            NavigationLine("(((({<>}<{<{<>}{[]{[]{}"),
            NavigationLine("[[<[([]))<([[{}[[()]]]"),
            NavigationLine("[{[{({}]{}}([{[{{{}}([]"),
            NavigationLine("{<[[]]>}<{[{[{[]{()[[[]"),
            NavigationLine("[<(<(<(<{}))><([]([]()"),
            NavigationLine("<{([([[(<>()){}]>(<<{{"),
            NavigationLine("<{([{{}}[<[[[<>{}]]]>[]]"),
        )
    }

    @Test
    fun `sample navigation program has 5 corrupt lines`() {
        assertThatSeq(
            NavigationProgram(
                "[({(<(())[]>[[{[]{<()<>>",
                "[(()[<>])]({[<{<<[]>>(",
                "{([(<{}[<>[]}>{[]{[(<()>",
                "(((({<>}<{<{<>}{[]{[]{}",
                "[[<[([]))<([[{}[[()]]]",
                "[{[{({}]{}}([{[{{{}}([]",
                "{<[[]]>}<{[{[{[]{()[[[]",
                "[<(<(<(<{}))><([]([]()",
                "<{([([[(<>()){}]>(<<{{",
                "<{([{{}}[<[[[<>{}]]]>[]]",
            ).corruptedLines()
        ).containsExactly(
            corruptLineOf("{([(<{}[<>[]}>{[]{[(<()>", unexpectedBracket = '}'),
            corruptLineOf("[[<[([]))<([[{}[[()]]]", unexpectedBracket = ')'),
            corruptLineOf("[{[{({}]{}}([{[{{{}}([]", unexpectedBracket = ']'),
            corruptLineOf("[<(<(<(<{}))><([]([]()", unexpectedBracket = ')'),
            corruptLineOf("<{([([[(<>()){}]>(<<{{", unexpectedBracket = '>'),
        )
    }

    @Test
    fun `sample navigation program has 5 incomplete lines`() {
        assertThatSeq(
            NavigationProgram(
                "[({(<(())[]>[[{[]{<()<>>",
                "[(()[<>])]({[<{<<[]>>(",
                "{([(<{}[<>[]}>{[]{[(<()>",
                "(((({<>}<{<{<>}{[]{[]{}",
                "[[<[([]))<([[{}[[()]]]",
                "[{[{({}]{}}([{[{{{}}([]",
                "{<[[]]>}<{[{[{[]{()[[[]",
                "[<(<(<(<{}))><([]([]()",
                "<{([([[(<>()){}]>(<<{{",
                "<{([{{}}[<[[[<>{}]]]>[]]",
            ).incompleteLines()
        ).containsExactly(
            incompleteLineOf("[({(<(())[]>[[{[]{<()<>>", missingClosings = "}}]])})]"),
            incompleteLineOf("[(()[<>])]({[<{<<[]>>(", missingClosings = ")}>]})"),
            incompleteLineOf("(((({<>}<{<{<>}{[]{[]{}", missingClosings = "}}>}>))))"),
            incompleteLineOf("{<[[]]>}<{[{[{[]{()[[[]", missingClosings = "]]}}]}]}>"),
            incompleteLineOf("<{([{{}}[<[[[<>{}]]]>[]]", missingClosings = "])}>"),
        )
    }
}

