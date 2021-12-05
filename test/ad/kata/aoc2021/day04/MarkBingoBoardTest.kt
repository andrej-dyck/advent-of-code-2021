package ad.kata.aoc2021.day04

import ad.kata.aoc2021.parseIntList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MarkBingoBoardTest {

    @ParameterizedTest
    @CsvSource(
        "[1] -> [1]",
        "[1,2,3] -> [1,2,3]",
        "[1,26,2,27,3,28] -> [1,2,3]",
        "[3,2,1] -> [3,2,1]",
        "[11,6,25] -> [11,6,25]",
        delimiterString = "->"
    )
    fun `accepts drawn numbers that are hits (in order)`(
        drawnNumbers: String,
        expectedHits: String
    ) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .withAcceptedNumbers(drawnNumbers.parseIntList())
                .hits()
        ).containsExactlyElementsOf(
            expectedHits.parseIntList()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[1,2,3,4,5,6] -> [1,2,3,4,5]",
        "[1,6,11,16,21,2] -> [1,6,11,16,21]",
        "[25,20,15,10,5,7] -> [25,20,15,10,5]",
        delimiterString = "->"
    )
    fun `does not accept any more numbers after bingo`(
        drawnNumbers: String,
        expectedHits: String
    ) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .withAcceptedNumbers(drawnNumbers.parseIntList())
                .hits()
        ).containsExactlyElementsOf(
            expectedHits.parseIntList()
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["[]", "[26]", "[26,57,99]"])
    fun `has no hits when all drawn numbers are misses`(drawnNumbers: String) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .withAcceptedNumbers(drawnNumbers.parseIntList())
                .hits()
        ).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "[1,2,3,4,5]",
            "[6,7,8,9,10]",
            "[11,12,13,14,15]",
            "[16,17,18,19,20]",
            "[21,22,23,24,25]",
            "[1,6,11,16,21]",
            "[2,7,12,17,22]",
            "[3,8,13,18,23]",
            "[4,9,14,19,24]",
            "[5,10,15,20,25]",
        ]
    )
    fun `is bingo when hits complete a row or col`(drawnNumbers: String) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .withAcceptedNumbers(drawnNumbers.parseIntList())
                .isBingo()
        ).`as`("is bingo").isTrue
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
    fun `is not bingo none of the rows or cols are complete`(drawnNumbers: String) {
        assertThat(
            """ 1  6 11 16 21
                2  7 12 17 22
                3  8 13 18 23
                4  9 14 19 24
                5 10 15 20 25"""
                .parseBingoBoard()
                .withAcceptedNumbers(drawnNumbers.parseIntList())
                .isBingo()
        ).`as`("is bingo").isFalse
    }
}

internal fun BingoBoard.withAcceptedNumbers(drawnNumbers: Iterable<Int>) =
    drawnNumbers.fold(this) { b, n -> b.acceptNumber(n) }

internal fun BingoBoard.withAcceptedNumbers(vararg drawnNumbers: Int) =
    withAcceptedNumbers(drawnNumbers.asIterable())

internal fun BingoBoard.withAcceptedNumbers(drawnNumbers: Sequence<Int>) =
    withAcceptedNumbers(drawnNumbers.asIterable())