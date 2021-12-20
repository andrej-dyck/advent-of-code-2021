package ad.kata.aoc2021.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FoldInstructionTest {

    @ParameterizedTest
    @CsvSource(
        "0,0; 1",
        "0,1; 2",
        "0,6; 7",
        delimiter = ';'
    )
    fun `fold up does not change dots that are above the line`(
        dot: String,
        line: Int
    ) {
        assertThat(
            FoldUp(y = line).project(dot.toDot())
        ).isEqualTo(
            dot.toDot()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,1; 1; 0,0",
        "0,2; 2; 0,1",
        "0,2; 1; 0,0",
        "0,10; 5; 0,0",
        "0,8; 5; 0,2",
        "0,5; 5; 0,4",
        "0,14; 7; 0,0",
        delimiter = ';'
    )
    fun `fold up projects dots in y direction that are below the line while by keeping the distance to the line`(
        dot: String,
        line: Int,
        expectedProjectedDot: String
    ) {
        assertThat(
            FoldUp(y = line).project(dot.toDot())
        ).isEqualTo(
            expectedProjectedDot.toDot()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,0; 1",
        "1,0; 2",
        "6,0; 7",
        delimiter = ';'
    )
    fun `fold left does not change dots that are left of the line`(
        dot: String,
        line: Int
    ) {
        assertThat(
            FoldLeft(x = line).project(dot.toDot())
        ).isEqualTo(
            dot.toDot()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "1,0; 1; 0,0",
        "2,0; 2; 1,0",
        "2,0; 1; 0,0",
        "10,0; 5; 0,0",
        "8,0; 5; 2,0",
        "5,0; 5; 4,0",
        "14,0; 7; 0,0",
        delimiter = ';'
    )
    fun `fold left projects dots in x direction that are right the line while by keeping the distance to the line`(
        dot: String,
        line: Int,
        expectedProjectedDot: String
    ) {
        assertThat(
            FoldLeft(x = line).project(dot.toDot())
        ).isEqualTo(
            expectedProjectedDot.toDot()
        )
    }

    @Test
    fun `can fold a paper up`() {
        assertThat(
            paperOfDots(
                "...#..#..#.",
                "....#......",
                "...........",
                "#..........",
                "...#....#.#",
                "...........",
                "...........",
                "...........",
                "...........",
                "...........",
                ".#....#.##.",
                "....#......",
                "......#...#",
                "#..........",
                "#.#........",
            ).fold(FoldUp(y = 7))
        ).isEqualTo(
            paperOfDots(
                "#.##..#..#.",
                "#...#......",
                "......#...#",
                "#...#......",
                ".#.#..#.###",
                "...........",
                "...........",
            )
        )
    }

    @Test
    fun `can fold a paper to the left`() {
        assertThat(
            paperOfDots(
                "#.##..#..#.",
                "#...#......",
                "......#...#",
                "#...#......",
                ".#.#..#.###",
                "...........",
                "...........",
            ).fold(FoldLeft(x = 5))
        ).isEqualTo(
            paperOfDots(
                "#####",
                "#...#",
                "#...#",
                "#...#",
                "#####",
                ".....",
                ".....",
            )
        )
    }
}