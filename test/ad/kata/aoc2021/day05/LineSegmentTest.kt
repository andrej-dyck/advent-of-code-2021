package ad.kata.aoc2021.day05

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.parseSequence
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LineSegmentTest {

    @ParameterizedTest
    @CsvSource(
        "(0,0)->(0,0); (0,0)",
        "(0,0)->(1,0); (0,0)^(1,0)",
        "(0,0)->(0,1); (0,0)^(0,1)",
        "(0,0)->(2,0); (0,0)^(1,0)^(2,0)",
        "(0,0)->(0,2); (0,0)^(0,1)^(0,2)",
        "(0,0)->(1,1); (0,0)^(1,1)",
        "(0,0)->(2,2); (0,0)^(1,1)^(2,2)",
        "(2,2)->(0,0); (2,2)^(1,1)^(0,0)",
        "(1,1)->(1,3); (1,1)^(1,2)^(1,3)",
        "(9,7)->(7,7); (9,7)^(8,7)^(7,7)",
        "(8,0)->(0,8); (8,0)^(7,1)^(6,2)^(5,3)^(4,4)^(3,5)^(2,6)^(1,7)^(0,8)",
        delimiter = ';'
    )
    fun `line segments can be transformed to points that are on this segment`(
        lineSegment1: String,
        expectedPoints: String
    ) {
        assertThatSeq(
            parseLineSegment(lineSegment1).points()
        ).containsExactlyElementsOf(
            expectedPoints.parseSequence { it.toPoint() }.asIterable()
        )
    }
}
