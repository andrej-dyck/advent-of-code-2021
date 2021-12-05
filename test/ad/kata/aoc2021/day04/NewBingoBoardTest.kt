package ad.kata.aoc2021.day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NewBingoBoardTest {

    @ParameterizedTest
    @ValueSource(
        strings = [
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25""",
            """22 13 17 11  0
                8  2 23  4 24
               21  9 14 16  7
                6 10  3 18  5
                1 12 20 15 19""",
            """ 3 15  0  2 22
                9 18 13 17  5
               19  8  7 25 23
               20 11 10 24  4
               14 21 16 12  6""",
            """14 21 17 24  4
               10 16 15  9 19
               18  8 23 26 20
               22 11 13  6  5
                2  0 12  3  7""",
        ]
    )
    fun `can create bingo board from string`(board: String) {
        assertThat(
            board.parseBingoBoard().numbers()
        ).containsExactlyElementsOf(
            board.trim().split("\\s+".toRegex()).map(String::toInt)
        )
    }

    @Test
    fun `new board has no hits`() {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .hits()
        ).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            """""",
            """14""",
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24""",
            """ 1  6 11 16
                2  7 12 17
                3  8 13 18
                4  9 14 19
                5 10 15 20""",
        ]
    )
    fun `throws when it's not a 5 x 5 matrix`(board: String) {
        assertThrows<IllegalArgumentException> {
            board.parseBingoBoard()
        }
    }

    @Test
    fun `throws when it has duplicate numbers`() {
        assertThrows<IllegalArgumentException> {
            """ 1  1 11 16 21
                1  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
        }
    }

    @Test
    fun `throws when it comprises negative numbers`() {
        assertThrows<IllegalArgumentException> {
            """-1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
        }
    }
}

internal fun String.parseBingoBoard() = bingoBoardFromLines(lines())