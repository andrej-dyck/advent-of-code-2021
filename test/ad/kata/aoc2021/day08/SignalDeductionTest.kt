package ad.kata.aoc2021.day08

import ad.kata.aoc2021.parseList
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SignalDeductionTest {

    @ParameterizedTest
    @CsvSource(
        "gc | gc | 1",
        "gc | cg | 1",
        "ed | ed | 1",
        "ed | de | 1",
        "cgb | cgb | 7",
        "cgb | bgc | 7",
        "fbg | bgf | 7",
        "fbg | gfb | 7",
        "gaef | fgae | 4",
        "cefg | cefg | 4",
        "fgec | cefg | 4",
        "cgeb | gcbe | 4",
        "cfbegad | fdgacbe | 8",
        "gcadebf | dgebacf | 8",
        "aecbfdg | egdcabf | 8",
        "bdacfeg | gebdcfa | 8",
        delimiter = '|'
    )
    fun `can identify unique length digits (1, 4, 7, 8)`(
        uniqueSignals: String,
        outputSignals: String,
        expectedOutputDigits: String
    ) {
        Assertions.assertThat(
            SignalDeduction(listOfSignals(uniqueSignals))
                .deduceDigits(listOfSignals(outputSignals))
        ).containsExactlyElementsOf(
            expectedOutputDigits.parseList { Digit(it.toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "eafb dab gcdfa fbcad cdfbe | gcdfa | 2",
        "eafb dab gcdfa fbcad cdfbe | fbcad | 3",
        "eafb dab gcdfa fbcad cdfbe | cdfbe | 5",
        delimiter = '|'
    )
    fun `can identify size 5 digits (2, 3, 5) with identified digits 4 and 7`(
        uniqueSignals: String,
        outputSignals: String,
        expectedOutputDigits: String
    ) {
        Assertions.assertThat(
            SignalDeduction(listOfSignals(uniqueSignals))
                .deduceDigits(listOfSignals(outputSignals))
        ).containsExactlyElementsOf(
            expectedOutputDigits.parseList { Digit(it.toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "fbcad dab cagedb cdfgeb cefabd | cagedb | 0",
        "fbcad dab cagedb cdfgeb cefabd | cdfgeb | 6",
        "fbcad dab cagedb cdfgeb cefabd | cefabd | 9",
        delimiter = '|'
    )
    fun `can identify size 6 digits (0, 6, 9) with identified digits 3 and 7`(
        uniqueSignals: String,
        outputSignals: String,
        expectedOutputDigits: String
    ) {
        Assertions.assertThat(
            SignalDeduction(listOfSignals(uniqueSignals))
                .deduceDigits(listOfSignals(outputSignals))
        ).containsExactlyElementsOf(
            expectedOutputDigits.parseList { Digit(it.toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf | 5,3,5,3",
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe | 8,3,9,4",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc | 9,7,8,1",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg | 1,1,9,7",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb | 9,3,6,1",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea | 4,8,7,3",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb | 8,4,1,8",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe | 4,5,4,8",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef | 1,6,2,5",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb | 8,7,1,7",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce | 4,3,1,5",
        delimiter = '|'
    )
    fun `can deduce digits from unique signals`(
        uniqueSignals: String,
        outputSignals: String,
        expectedOutputDigits: String
    ) {
        Assertions.assertThat(
            SignalDeduction(listOfSignals(uniqueSignals))
                .deduceDigits(listOfSignals(outputSignals))
        ).containsExactlyElementsOf(
            expectedOutputDigits.parseList { Digit(it.toInt()) }
        )
    }
}