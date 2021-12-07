package ad.kata.aoc2021.day06

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.parseIntPair
import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LanternfishPopulationTest {

    @ParameterizedTest
    @CsvSource(
        "(0,1); 1",
        "(0,1)^(1,1); 2",
        "(0,1)^(1,1)^(2,1); 3",
        "(0,3); 3",
        "(0,3)^(1,2)^(2,5)^(3,7); 17",
        "(1,1)^(2,1)^(3,2)^(4,1); 5",
        delimiter = ';'
    )
    fun `knows the total amount of fish`(
        population: String,
        expectedAmount: Long
    ) {
        assertThat(
            LanternfishPopulation(population.parsePopulationMap()).totalAmount()
        ).isEqualTo(
            Amount(expectedAmount)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "(1,1); (0,1)",
        "(2,1); (1,1)",
        "(3,1); (2,1)",
        "(4,1); (3,1)",
        "(5,1); (4,1)",
        "(6,1); (5,1)",
        "(7,1); (6,1)",
        "(8,1); (7,1)",
        "(1,1)^(2,1)^(3,2)^(4,1); (0,1)^(1,1)^(2,2)^(3,1)",
        "(1,1)^(2,1)^(3,3)^(4,2)^(5,2)^(6,1); (0,1)^(1,1)^(2,3)^(3,2)^(4,2)^(5,1)",
        delimiter = ';'
    )
    fun `decreases the internal timer of all fish after 1 day`(
        population: String,
        expectedNextDayPopulation: String
    ) {
        assertThat(
            LanternfishPopulation(population.parsePopulationMap()).nextDay()
        ).isEqualTo(
            LanternfishPopulation(expectedNextDayPopulation.parsePopulationMap())
        )
    }

    @ParameterizedTest
    @CsvSource(
        "(0,1); (6,1)^(8,1)",
        "(0,2); (6,2)^(8,2)",
        "(0,3); (6,3)^(8,3)",
        "(0,7); (6,7)^(8,7)",
        delimiter = ';'
    )
    fun `fish with internal timer of 0 reproduce (new fish with 8) and timer resets to 6 the next day`(
        population: String,
        expectedNextDayPopulation: String
    ) {
        assertThat(
            LanternfishPopulation(population.parsePopulationMap()).nextDay()
        ).isEqualTo(
            LanternfishPopulation(expectedNextDayPopulation.parsePopulationMap())
        )
    }

    @Test
    fun `simulates fish population over days`() {
        assertThatSeq(
            LanternfishPopulation(
                lanternfishTimersOf(3, 4, 3, 1, 2)
            ).simulateDays().drop(1).take(10)
        ).containsExactly(
            LanternfishPopulation(
                lanternfishTimersOf(2, 3, 2, 0, 1)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(1, 2, 1, 6, 0, 8)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(0, 1, 0, 5, 6, 7, 8)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(6, 0, 6, 4, 5, 6, 7, 8, 8)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(5, 6, 5, 3, 4, 5, 6, 7, 7, 8)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(4, 5, 4, 2, 3, 4, 5, 6, 6, 7)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(3, 4, 3, 1, 2, 3, 4, 5, 5, 6)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(2, 3, 2, 0, 1, 2, 3, 4, 4, 5)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(1, 2, 1, 6, 0, 1, 2, 3, 3, 4, 8)
            ),
            LanternfishPopulation(
                lanternfishTimersOf(0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 7, 8)
            )
        )
    }

    @Test
    fun `reads lanternfish population state from input`() {
        assertThat(
            lanternfishPopulationFromInput("day06.input-sample")
        ).isEqualTo(
            LanternfishPopulation(
                lanternfishTimersOf(3, 4, 3, 1, 2)
            )
        )
    }

    @ParameterizedTest
    @CsvSource(
        "1, 5",
        "2, 6",
        "7, 10",
        "18, 26",
        "80, 5934",
    )
    fun `sample input total population after N days`(days: Int, expectedAmount: Long) {
        assertThat(
            LanternfishPopulation(
                lanternfishTimersOf(3, 4, 3, 1, 2)
            ).afterDays(days).totalAmount()
        ).isEqualTo(
            Amount(expectedAmount)
        )
    }
}

internal fun String.parsePopulationMap() =
    parseSequence { it.parseIntPair() }
        .map { LanternfishTimer(it.first) to Amount(it.second.toLong()) }
        .toMap()