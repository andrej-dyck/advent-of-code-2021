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
        "0,1,2 -> 2", // 2x 1 fuel
        "0,2,4 -> 6", // 2x 3 fuel
        "0,3,6 -> 12", // 2x 6 fuel
        "0,4,8 -> 20", // 2x 10 fuel
        "0,5,10 -> 30", // 2x 15 fuel
        "1,5,9 -> 20", // 2x 10 fuel
        "2,5,8 -> 12", // 2x 6 fuel
        "4,5,6 -> 2", // 2x 1 fuel
        "7,5,3 -> 6", // 2x 3 fuel
        "19,10,1 -> 90", // 2x 45 fuel
        "22,11,0 -> 132", // 2x 66 fuel
        delimiterString = "->"
    )
    fun `crab submarine engines consume fuel exponentially (gauss sum) to move distance`(
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
    @CsvSource(
        "1 -> 0",
        "1,1 -> 0",
        "1,2 -> 1",
        "1,3 -> 2",
        "1,5,1 -> 8", // pos 2 is optimal
        "2,1,2 -> 1",
        "3,1,3 -> 3", // pos 2 is optimal
        "4,2,0 -> 6",
        "16,1,2,0,4,2,7,1,2,14 -> 168", // pos 5 is optimal
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
            Fuel(168)
        )
    }
}

internal fun horizontalPositionsOf(vararg positions: Int) =
    horizontalPositionsOf(positions.toList())