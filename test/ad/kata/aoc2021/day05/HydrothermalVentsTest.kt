package ad.kata.aoc2021.day05

import ad.kata.aoc2021.parseSequence
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HydrothermalVentsTest {

    @Test
    fun `has no overlapping points when field is empty`() {
        assertThat(
            HydrothermalVentsField(emptySet()).pointsOfOverlaps()
        ).isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "(1,1)->(1,3)",
        "(1,1)->(1,3)^(0,1)->(0,3)",
        "(1,1)->(1,3)^(9,7)->(7,7)",
        "(1,1)->(1,3)^(4,4)->(9,4)^(9,7)->(7,7)^(3,4)->(1,4)",
        delimiter = ';'
    )
    fun `has no overlapping points when no lines overlap`(
        lineSegments: String
    ) {
        assertThat(
            HydrothermalVentsField(
                lineSegments.parseSequence { parseLineSegment(it) }.toSet()
            ).pointsOfOverlaps()
        ).isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "(1,1)->(1,3)^(0,1)->(1,1); (1,1)",
        "(1,1)->(1,3)^(0,2)->(3,2); (1,2)",
        "(7,0)->(7,4)^9,4 -> 3,4^(3,4)->(1,4); (3,4)^(7,4)",
        "(0,9)->(2,9)^(0,9)->(5,9); (0,9)^(1,9)^(2,9)",
        delimiter = ';'
    )
    fun `lists overlapping points when horizontal and vertical lines overlap`(
        lineSegments: String,
        expectedOverlappingPoints: String
    ) {
        assertThat(
            HydrothermalVentsField(
                lineSegments.parseSequence { parseLineSegment(it) }.toSet()
            ).pointsOfOverlaps()
        ).containsExactlyInAnyOrderElementsOf(
            expectedOverlappingPoints.parseSequence { it.toPoint() }.asIterable()
        )
    }

    @Test
    fun `reads hydrothermal vents field from input`() {
        assertThat(
            hydrothermalVentsFromInput("day05.input-sample").linesOfVents
        ).containsExactlyInAnyOrder(
            Point(0, 9) to Point(5, 9),
            Point(8, 0) to Point(0, 8),
            Point(9, 4) to Point(3, 4),
            Point(2, 2) to Point(2, 1),
            Point(7, 0) to Point(7, 4),
            Point(6, 4) to Point(2, 0),
            Point(0, 9) to Point(2, 9),
            Point(3, 4) to Point(1, 4),
            Point(0, 0) to Point(8, 8),
            Point(5, 5) to Point(8, 2)
        )
    }

    @Test
    fun `sample input has 5 points of overlaps of only horizontal and vertical lines`() {
        assertThat(
            HydrothermalVentsField(
                setOf(
                    Point(0, 9) to Point(5, 9),
                    Point(8, 0) to Point(0, 8),
                    Point(9, 4) to Point(3, 4),
                    Point(2, 2) to Point(2, 1),
                    Point(7, 0) to Point(7, 4),
                    Point(6, 4) to Point(2, 0),
                    Point(0, 9) to Point(2, 9),
                    Point(3, 4) to Point(1, 4),
                    Point(0, 0) to Point(8, 8),
                    Point(5, 5) to Point(8, 2)
                )
            ).filterNoDiagonals().pointsOfOverlaps()
        ).containsExactlyInAnyOrder(
            Point(3, 4),
            Point(7, 4),
            Point(0, 9),
            Point(1, 9),
            Point(2, 9)
        )
    }
}