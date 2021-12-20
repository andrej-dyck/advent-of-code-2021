package ad.kata.aoc2021.day14

import ad.kata.aoc2021.assertThatSeq
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PolymerizationTest {

    private val sampleInputPolymerization = Polymerization(
        template = Polymer("NNCB"),
        insertionRulesOf(
            "CH" to 'B',
            "HH" to 'N',
            "CB" to 'H',
            "NH" to 'C',
            "HB" to 'C',
            "HC" to 'B',
            "HN" to 'C',
            "NN" to 'C',
            "BH" to 'H',
            "NC" to 'B',
            "NB" to 'B',
            "BN" to 'B',
            "BB" to 'N',
            "BC" to 'B',
            "CC" to 'N',
            "CN" to 'C',
        )
    )

    @Test
    fun `can perform pair insertion process`() {
        assertThatSeq(
            sampleInputPolymerization.processResults().take(4)
        ).containsExactly(
            Polymer("NCNBCHB"),
            Polymer("NBCCNBBBCBHCB"),
            Polymer("NBBBCNCCNBBNBNBBCHBHHBCHB"),
            Polymer("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"),
        )
    }

    @ParameterizedTest
    @CsvSource("1, 7", "2, 13", "5, 97", "10, 3073")
    fun `check sample input growth`(numberOfSteps: Int, expectedNumberOfElements: Int) {
        assertThat(
            sampleInputPolymerization.processResults()
                .take(numberOfSteps)
                .last()
                .length()
        ).isEqualTo(
            expectedNumberOfElements
        )
    }

    @Test
    fun `reads polymerization template from input`() {
        assertThat(
            polymerizationFromInput("day14.input-sample").template
        ).isEqualTo(
            sampleInputPolymerization.template
        )
    }

    @Test
    fun `reads polymerization insertion rules from input`() {
        assertThat(
            polymerizationFromInput("day14.input-sample").insertionRules
        ).isEqualTo(
            sampleInputPolymerization.insertionRules
        )
    }
}