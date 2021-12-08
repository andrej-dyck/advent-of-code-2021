package ad.kata.aoc2021.day08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DisplayTroubleshootingTest {

    @ParameterizedTest
    @ValueSource(strings = ["ab", "gc", "cg", "ca", "fg"])
    fun `can identify 1 due to its unique number of segments (2)`(segments: String) {
        assertThat(
            entryOf(outputValues = segments).identifiedDigits()
        ).containsExactly(
            Digit(1)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["bcdf", "gcbe", "gecf", "ecba", "cefg"])
    fun `can identify 4 due to its unique number of segments (4)`(segments: String) {
        assertThat(
            entryOf(outputValues = segments).identifiedDigits()
        ).containsExactly(
            Digit(4)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["acf", "cgb", "cbg", "bgf"])
    fun `can identify 7 due to its unique number of segments (3)`(segments: String) {
        assertThat(
            entryOf(outputValues = segments).identifiedDigits()
        ).containsExactly(
            Digit(7)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["abcdefg", "dgebacf", "egdcabf", "gebdcfa", "fadegcb", "gbcadfe", "gbdfcae"])
    fun `can identify 8 due to its unique number of segments (7)`(segments: String) {
        assertThat(
            entryOf(outputValues = segments).identifiedDigits()
        ).containsExactly(
            Digit(8)
        )
    }

    @Test
    fun `reads display troubleshooting entries from input`() {
        assertThat(
            troubleShootingFromInput("day08.input-sample").entries
        ).containsExactly(
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb"),
                outputValues = listOfSegments("fdgacbe cefdb cefbgd gcbe")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec"),
                outputValues = listOfSegments("fcgedb cgb dgebacf gc")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef"),
                outputValues = listOfSegments("cg cg fdcagb cbg")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega"),
                outputValues = listOfSegments("efabcd cedba gadfec cb")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga"),
                outputValues = listOfSegments("gecf egdcabf bgf bfgea")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf"),
                outputValues = listOfSegments("gebdcfa ecba ca fadegcb")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf"),
                outputValues = listOfSegments("cefg dcbef fcge gbcadfe")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd"),
                outputValues = listOfSegments("ed bcgafe cdgba cbgef")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg"),
                outputValues = listOfSegments("gbdfcae bgc cg cgb")
            ),
            TroubleshootingEntry(
                uniqueSignalPatterns = listOfSegments("gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc"),
                outputValues = listOfSegments("fgae cfgab fg bagce")
            ),
        )
    }

    @Test
    fun `digits 1,4,7,8 appear 26 times in the output values of the sample data`() {
        assertThat(
            DisplayTroubleshooting(
                entryOf(outputValues = "fdgacbe cefdb cefbgd gcbe"),
                entryOf(outputValues = "fcgedb cgb dgebacf gc"),
                entryOf(outputValues = "cg cg fdcagb cbg"),
                entryOf(outputValues = "efabcd cedba gadfec cb"),
                entryOf(outputValues = "gecf egdcabf bgf bfgea"),
                entryOf(outputValues = "gebdcfa ecba ca fadegcb"),
                entryOf(outputValues = "cefg dcbef fcge gbcadfe"),
                entryOf(outputValues = "ed bcgafe cdgba cbgef"),
                entryOf(outputValues = "gbdfcae bgc cg cgb"),
                entryOf(outputValues = "fgae cfgab fg bagce"),
            ).identifiedOutputDigits().count()
        ).isEqualTo(
            26
        )
    }
}

private fun listOfSegments(segments: String) =
    segments.split(' ').map { SegmentsDigit(it) }

private fun entryOf(uniqueSignalPatterns: String = "", outputValues: String = "") =
    TroubleshootingEntry(
        uniqueSignalPatterns = listOfSegments(uniqueSignalPatterns),
        outputValues = listOfSegments(outputValues)
    )