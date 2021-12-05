package ad.kata.aoc2021.day04

import ad.kata.aoc2021.parseIntArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BoardScoreTest {

    @ParameterizedTest
    @CsvSource(
        "[5,4,3,2,1]; 310", // 310 * 1
        "[1,2,3,4,5]; 1550", // 310 * 5
        "[5,2,1,4,3]; 930", // 310 * 3
        "[25,15,5,20,10]; 2500", // 250 * 10
        "[25,20,15,10,5]; 1250", // 250 * 5
        "[5,10,15,20,25]; 6250", // 250 * 25
        delimiter = ';'
    )
    fun `score is sum of unmarked numbers times last hit`(
        drawnNumbers: String,
        expectedScore: Int
    ) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .acceptNumbers(*drawnNumbers.parseIntArray())
                .score()
        ).isEqualTo(
            expectedScore
        )
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[]",
            "[1]",
            "[1,2,3,4]",
            "[1,6,11,16]",
            "[1,7,13,19,25]",
            "[1,2,3,4,6,7,8,10,11,12,14,15,16,18,19,20,22,23,24,25]",
        ]
    )
    fun `score is zero when there is no bingo`(drawnNumbers: String) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .acceptNumbers(*drawnNumbers.parseIntArray())
                .score()
        ).isZero
    }

    @Test
    fun `score of example board and draw is 4512`() {
        assertThat(
            """14 21 17 24  4
               10 16 15  9 19
               18  8 23 26 20
               22 11 13  6  5
                2  0 12  3  7"""
                .parseBingoBoard()
                .acceptNumbers(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24)
                .score()
        ).isEqualTo(
            4512
        )
    }
}