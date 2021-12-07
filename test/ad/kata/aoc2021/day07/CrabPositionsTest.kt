package ad.kata.aoc2021.day07

import ad.kata.aoc2021.extensions.toInts
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class CrabPositionsTest {

    @ParameterizedTest
    @CsvSource(
        "1 -> 0",
        "1,1 -> 0",
        "1,2 -> 1",
        "2,1,2 -> 1",
        "2,1,1 -> 1",
        "2,1,0 -> 2",
        "3,1,0 -> 3",
        "3,1,4 -> 3",
        "7,3,1 -> 6",
        "16,1,2,0,4,2,7,1,2,14 -> 37",
        delimiterString = "->"
    )
    fun `alignment costs is the minimal fuel needed to align all crabs to 1 position`(
        positions: String,
        expectedFuel: Int
    ) {
        assertThat(
            CrabPositions(horizontalPositionsOf(positions.toInts())).alignmentCosts()
        ).isEqualTo(
            Fuel(expectedFuel)
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 5, 7, 17, 101, 999])
    fun `alignment costs 0 fuel for only 1 crab`(position: Int) {
        assertThat(
            CrabPositions(horizontalPositionsOf(position)).alignmentCosts()
        ).isEqualTo(
            Fuel(0)
        )
    }

    @Test
    fun `alignment costs 0 fuel when all crabs are at the same position`() {
        assertThat(
            CrabPositions(horizontalPositionsOf(1, 1, 1, 1, 1)).alignmentCosts()
        ).isEqualTo(
            Fuel(0)
        )
    }

    @Test
    fun `reads crab positions from input`() {
        assertThat(
            crabPositionsFromInput("day07.input-sample")
                .horizontalPositions
        ).containsExactlyElementsOf(
            horizontalPositionsOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
        )
    }

    @Test
    fun `cheapest alignment of sample input crabs, considering fuel cost, is 37 fuel`() {
        assertThat(
            CrabPositions(
                horizontalPositionsOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
            ).alignmentCosts()
        ).isEqualTo(
            Fuel(37)
        )
    }
}

internal fun horizontalPositionsOf(vararg positions: Int) =
    horizontalPositionsOf(positions.toList())