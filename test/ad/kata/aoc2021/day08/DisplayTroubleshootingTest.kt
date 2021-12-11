package ad.kata.aoc2021.day08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DisplayTroubleshootingTest {

    @ParameterizedTest
    @CsvSource(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf | 5353",
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe | 8394",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc | 9781",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg | 1197",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb | 9361",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea | 4873",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb | 8418",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe | 4548",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef | 1625",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb | 8717",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce | 4315",
        delimiter = '|'
    )
    fun `can deduce output value from unique signals`(
        uniqueSignals: String,
        outputSignals: String,
        expectedOutputValue: Int
    ) {
        assertThat(
            entryOf(
                uniqueSignals = uniqueSignals,
                outputSignals = outputSignals
            ).deduceOutputValue()
        ).isEqualTo(
            expectedOutputValue
        )
    }

    @Test
    fun `reads display troubleshooting entries from input`() {
        assertThat(
            troubleShootingFromInput("day08.input-sample").entries
        ).containsExactly(
            entryOf(
                uniqueSignals = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb",
                outputSignals = "fdgacbe cefdb cefbgd gcbe"
            ),
            entryOf(
                uniqueSignals = "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec",
                outputSignals = "fcgedb cgb dgebacf gc"
            ),
            entryOf(
                uniqueSignals = "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef",
                outputSignals = "cg cg fdcagb cbg"
            ),
            entryOf(
                uniqueSignals = "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega",
                outputSignals = "efabcd cedba gadfec cb"
            ),
            entryOf(
                uniqueSignals = "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga",
                outputSignals = "gecf egdcabf bgf bfgea"
            ),
            entryOf(
                uniqueSignals = "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf",
                outputSignals = "gebdcfa ecba ca fadegcb"
            ),
            entryOf(
                uniqueSignals = "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf",
                outputSignals = "cefg dcbef fcge gbcadfe"
            ),
            entryOf(
                uniqueSignals = "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd",
                outputSignals = "ed bcgafe cdgba cbgef"
            ),
            entryOf(
                uniqueSignals = "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg",
                outputSignals = "gbdfcae bgc cg cgb"
            ),
            entryOf(
                uniqueSignals = "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc",
                outputSignals = "fgae cfgab fg bagce"
            ),
        )
    }

    @Test
    fun `sum of output values of the sample data is 61229`() {
        assertThat(
            DisplayTroubleshooting(
                entryOf(
                    uniqueSignals = "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb",
                    outputSignals = "fdgacbe cefdb cefbgd gcbe"
                ),
                entryOf(
                    uniqueSignals = "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec",
                    outputSignals = "fcgedb cgb dgebacf gc"
                ),
                entryOf(
                    uniqueSignals = "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef",
                    outputSignals = "cg cg fdcagb cbg"
                ),
                entryOf(
                    uniqueSignals = "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega",
                    outputSignals = "efabcd cedba gadfec cb"
                ),
                entryOf(
                    uniqueSignals = "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga",
                    outputSignals = "gecf egdcabf bgf bfgea"
                ),
                entryOf(
                    uniqueSignals = "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf",
                    outputSignals = "gebdcfa ecba ca fadegcb"
                ),
                entryOf(
                    uniqueSignals = "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf",
                    outputSignals = "cefg dcbef fcge gbcadfe"
                ),
                entryOf(
                    uniqueSignals = "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd",
                    outputSignals = "ed bcgafe cdgba cbgef"
                ),
                entryOf(
                    uniqueSignals = "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg",
                    outputSignals = "gbdfcae bgc cg cgb"
                ),
                entryOf(
                    uniqueSignals = "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc",
                    outputSignals = "fgae cfgab fg bagce"
                )
            ).deducedOutputValues().filterNotNull().sum()
        ).isEqualTo(
            61229
        )
    }
}

private fun entryOf(uniqueSignals: String = "", outputSignals: String) =
    TroubleshootingEntry(
        uniqueSignals = listOfSignals(uniqueSignals),
        outputSignals = listOfSignals(outputSignals)
    )