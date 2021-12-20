package ad.kata.aoc2021.day14

import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class InsertionRulesTest {

    @ParameterizedTest
    @CsvSource(
        "AB, AB->C, ACB",
        "AAB, AA->C^AB->D, ACADB",
        "NNCB, NN->C^NC->B^CB -> H, NCNBCHB",
    )
    fun `applies all matching rules to polymer by inserting the element between element pairs`(
        polymerTemplate: String,
        rules: String,
        expectedPolymer: String
    ) {
        assertThat(
            InsertionRules(
                rules.parseSequence { it.parseInsertionRule() }
            ).pairInsertionOn(
                Polymer(polymerTemplate)
            )
        ).isEqualTo(
            Polymer(expectedPolymer)
        )
    }

    @Test
    fun `does not change polymer when no rules exit`() {
        assertThat(
            insertionRulesOf().pairInsertionOn(Polymer("ABCD"))
        ).isEqualTo(
            Polymer("ABCD")
        )
    }

    @Test
    fun `does not change polymer when no rules apply`() {
        assertThat(
            insertionRulesOf(
                "CB" to 'A',
                "AD" to 'C',
                "AC" to 'D',
            ).pairInsertionOn(Polymer("ABCD"))
        ).isEqualTo(
            Polymer("ABCD")
        )
    }

    @Test
    fun `does not change polymer when template is only 1 element in size`() {
        assertThat(
            insertionRulesOf("AA" to 'C').pairInsertionOn(Polymer("A"))
        ).isEqualTo(
            Polymer("A")
        )
    }
}

internal fun insertionRulesOf(vararg rules: Pair<String, Char>) =
    InsertionRules(rules.associate { elementPairOf(it.first) to Element(it.second) })