package ad.kata.aoc2021.day09

import ad.kata.aoc2021.extensions.product
import ad.kata.aoc2021.parseList
import ad.kata.aoc2021.types.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BasinsTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            "313",
            "212",
            "4321",
            "14321",
            "1432012",
            "4|3|2|1",
            "1|4|3|2|1",
            "1|4|3|2|0|1|2",
            "333|232|333",
            "133|232|331",
            "2199943210",
            "9856789892",
            "2199943210|3987894921|9856789892|8767896789|9899965678"
        ]
    )
    fun `basins eventually flow into local low-points`(heightmap: String) {
        val map = heightmapOf(*heightmap.split('|').toTypedArray())

        assertThat(
            map.basins().flatMap { it.keys }
        ).containsAll(
            map.lowPoints().keys
        )
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "124",
            "1241",
            "14321",
            "1432012",
            "454|232|454",
            "333|232|333",
            "133|232|331",
            "2199943210",
            "9856789892",
            "2199943210|3987894921|9856789892|8767896789|9899965678"
        ]
    )
    fun `there are as many basins as low points`(heightmap: String) {
        val map = heightmapOf(*heightmap.split('|').toTypedArray())

        assertThat(
            map.basins()
        ).hasSize(
            map.lowPoints().size
        )
    }

    @ParameterizedTest
    @CsvSource(
        "191; 1,1",
        "789; 7,8",
        "9856789892; 5,8,6,7,8,8,2",
        delimiter = ';'
    )
    fun `locations of height 9 are not part of any basin`(
        heightmap: String,
        expectedBasinHeights: String
    ) {
        assertThat(
            heightmapOf(*heightmap.split('|').toTypedArray())
                .basins().flatMap { it.values }
        ).containsExactlyInAnyOrderElementsOf(
            expectedBasinHeights.parseList { Height(it.toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
//        "14; 1,4",
        "142; 1,4,2,4",
//        "1942; 1,2,4",
//        "1492; 1,4,2",
//        "2199943210; 1,2,0,1,2,3,4",
        delimiter = ';'
    )
    fun `basins contain adjacent heights starting at a low point up to heights smaller 9`(
        heightmap: String,
        expectedBasinHeights: String
    ) {
        assertThat(
            heightmapOf(*heightmap.split('|').toTypedArray())
                .basins().flatMap { it.values }
        ).containsExactlyInAnyOrderElementsOf(
            expectedBasinHeights.parseList { Height(it.toInt()) }
        )
    }

    @Test
    fun `sample height map has the following basins surrounding the low points 1, 0, 5, and 5`() {
        assertThat(
            heightmapOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).basins()
        ).containsExactlyInAnyOrder(
            // top-left basin
            basinOf(
                Coordinate(1, 0) to Height(1),
                Coordinate(0, 0) to Height(2),
                Coordinate(0, 1) to Height(3),
            ),
            // top-right basin
            basinOf(
                Coordinate(9, 0) to Height(0),
                Coordinate(8, 0) to Height(1),
                Coordinate(9, 1) to Height(1),
                Coordinate(7, 0) to Height(2),
                Coordinate(8, 1) to Height(2),
                Coordinate(9, 2) to Height(2),
                Coordinate(6, 0) to Height(3),
                Coordinate(5, 0) to Height(4),
                Coordinate(6, 1) to Height(4),
            ),
            // middle basin
            basinOf(
                Coordinate(2, 2) to Height(5),
                Coordinate(3, 2) to Height(6),
                Coordinate(2, 3) to Height(6),
                Coordinate(3, 1) to Height(7),
                Coordinate(4, 2) to Height(7),
                Coordinate(1, 3) to Height(7),
                Coordinate(3, 3) to Height(7),
                Coordinate(2, 1) to Height(8),
                Coordinate(4, 1) to Height(8),
                Coordinate(1, 2) to Height(8),
                Coordinate(5, 2) to Height(8),
                Coordinate(0, 3) to Height(8),
                Coordinate(4, 3) to Height(8),
                Coordinate(1, 4) to Height(8),
            ),
            // bottom basin
            basinOf(
                Coordinate(6, 4) to Height(5),
                Coordinate(5, 4) to Height(6),
                Coordinate(7, 4) to Height(6),
                Coordinate(6, 3) to Height(6),
                Coordinate(8, 4) to Height(7),
                Coordinate(7, 3) to Height(7),
                Coordinate(9, 4) to Height(8),
                Coordinate(8, 3) to Height(8),
                Coordinate(7, 2) to Height(8),
            )
        )
    }

    @Test
    fun `sample height map's 3 largest basins are of size 14, 9, 9`() {
        assertThat(
            heightmapOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).basins().takeLargest(3).map { it.size }
        ).containsExactly(14, 9, 9)
    }

    @Test
    fun `sample height map's product of 3 largest basins sizes is 1134`() {
        assertThat(
            heightmapOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).basins().takeLargest(3).map { it.size }.product()
        ).isEqualTo(1134L)
    }
}

internal fun basinOf(vararg locations: Pair<Coordinate, Height>) =
    mapOf(*locations)