package ad.kata.aoc2021.day09

import ad.kata.aoc2021.parseList
import ad.kata.aoc2021.types.Coordinate
import ad.kata.aoc2021.types.Matrix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HeightmapTest {

    @ParameterizedTest
    @CsvSource(
        "212; 1",
        "4321; 1",
        "14321; 1,1",
        "1432012; 1,0",
        "4|3|2|1; 1",
        "1|4|3|2|1; 1,1",
        "1|4|3|2|0|1|2; 1,0",
        "333|232|333; 2,2",
        "133|232|331; 1,1",
        "2199943210; 1,0",
        "9856789892; 5,8,2",
        delimiter = ';'
    )
    fun `all low points have a terrain that is higher`(
        heightmap: String,
        expectedLowPoints: String
    ) {
        assertThat(
            heightmapOf(
                *heightmap.split('|').toTypedArray()
            ).lowPoints().values
        ).containsExactlyInAnyOrderElementsOf(
            expectedLowPoints.parseList { Height(it.toInt()) }
        )
    }

    @Test
    fun `has no low points when map is empty`() {
        assertThat(
            heightmapOf().lowPoints()
        ).isEmpty()
    }

    @Test
    fun `reads heightmap from input`() {
        assertThat(
            heightmapFromInput("day09.input-sample").heights
        ).isEqualTo(
            matrixOfHeights(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            )
        )
    }

    @Test
    fun `sample height map has low points 1, 0, 5, and 5`() {
        assertThat(
            heightmapOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).lowPoints()
        ).containsExactlyEntriesOf(
            mapOf(
                Coordinate(1, 0) to Height(1),
                Coordinate(9, 0) to Height(0),
                Coordinate(2, 2) to Height(5),
                Coordinate(6, 4) to Height(5),
            )
        )
    }
}

internal fun heightmapOf(vararg rows: String) =
    Heightmap(matrixOfHeights(*rows))

internal fun matrixOfHeights(vararg rows: String) = Matrix(
    rows.map { r -> r.toCharArray().map { Height(it.digitToInt()) } }
)