package ad.kata.aoc2021.day13

import ad.kata.aoc2021.assertThatSeq
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrigamiTest {

    @Test
    fun `applies folding instruction to the given paper in sequence`() {
        assertThatSeq(
            Origami(
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
                ),
                sequenceOf(FoldUp(y = 7), FoldLeft(x = 5))
            ).folds()
        ).containsExactly(
            paperOfDots(
                "#.##..#..#.",
                "#...#......",
                "......#...#",
                "#...#......",
                ".#.#..#.###",
                "...........",
                "...........",
            ),
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

    @Test
    fun `reads paper with dots from input`() {
        assertThat(
            origamiFromInput("day13.input-sample").paper
        ).isEqualTo(
            Paper(
                6 to 10,
                0 to 14,
                9 to 10,
                0 to 3,
                10 to 4,
                4 to 11,
                6 to 0,
                6 to 12,
                4 to 1,
                0 to 13,
                10 to 12,
                3 to 4,
                3 to 0,
                8 to 4,
                1 to 10,
                2 to 14,
                8 to 10,
                9 to 0,
            )
        )
    }

    @Test
    fun `reads folding instructions from input`() {
        assertThatSeq(
            origamiFromInput("day13.input-sample").instructions
        ).containsExactly(
            FoldUp(y = 7),
            FoldLeft(x = 5)
        )
    }
}

internal fun paperOfDots(vararg lines: String) = paperOfDots(lines.toList())
internal fun paperOfDots(lines: List<String>) = Paper(
    lines.flatMapIndexed { y, c ->
        c.mapIndexed { x, v -> if (v == '#') Dot(x, y) else null }
    }.filterNotNull()
)