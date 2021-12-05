package ad.kata.aoc2021.day04

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.extensions.second
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BingoSystemTest {

    @Test
    fun `marks boards with drawn numbers in sequence`() {
        val firstBoard = """ 1  6 11 16 21
                             2  7 12 17 22
                             3  8 13 18 23
                             4  9 14 19 24
                             5 10 15 20 25""".parseBingoBoard()
        val secondBoard = """ 2 12 22 32 42
                              4 14 24 34 44
                              6 16 26 36 46
                              8 18 28 38 48
                             10 20 30 40 50""".parseBingoBoard()

        assertThatSeq(
            BingoSystem(
                numbersDrawSequence = sequenceOf(1, 2, 8, 42, 25),
                blankBoards = listOf(firstBoard, secondBoard)
            ).play()
        ).containsExactly(
            listOf(
                firstBoard.withAcceptedNumbers(1),
                secondBoard
            ),
            listOf(
                firstBoard.withAcceptedNumbers(1, 2),
                secondBoard.withAcceptedNumbers(2)
            ),
            listOf(
                firstBoard.withAcceptedNumbers(1, 2, 8),
                secondBoard.withAcceptedNumbers(2, 8)
            ),
            listOf(
                firstBoard.withAcceptedNumbers(1, 2, 8),
                secondBoard.withAcceptedNumbers(2, 8, 42)
            ),
            listOf(
                firstBoard.withAcceptedNumbers(1, 2, 8, 25),
                secondBoard.withAcceptedNumbers(2, 8, 42)
            )
        )
    }

    @Test
    fun `is capable to figure out which board wins first`() {
        val bingoSystem = BingoSystem(
            numbersDrawSequence = sequenceOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24),
            blankBoards = listOf(
                """22 13 17 11  0
                    8  2 23  4 24
                   21  9 14 16  7
                    6 10  3 18  5
                    1 12 20 15 19""".parseBingoBoard(),
                """ 3 15  0  2 22
                    9 18 13 17  5
                   19  8  7 25 23
                   20 11 10 24  4
                   14 21 16 12  6""".parseBingoBoard(),
                """14 21 17 24  4
                   10 16 15  9 19
                   18  8 23 26 20
                   22 11 13  6  5
                    2  0 12  3  7""".parseBingoBoard()
            )
        )

        assertThat(
            bingoSystem.firstWinningBoard()
        ).isEqualTo(
            bingoSystem.blankBoards.last()
                .withAcceptedNumbers(bingoSystem.numbersDrawSequence)
        )
    }

    @Test
    fun `is capable to figure out which board wins last`() {
        val bingoSystem = BingoSystem(
            numbersDrawSequence = sequenceOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13),
            blankBoards = listOf(
                """22 13 17 11  0
                    8  2 23  4 24
                   21  9 14 16  7
                    6 10  3 18  5
                    1 12 20 15 19""".parseBingoBoard(),
                """ 3 15  0  2 22
                    9 18 13 17  5
                   19  8  7 25 23
                   20 11 10 24  4
                   14 21 16 12  6""".parseBingoBoard(),
                """14 21 17 24  4
                   10 16 15  9 19
                   18  8 23 26 20
                   22 11 13  6  5
                    2  0 12  3  7""".parseBingoBoard()
            )
        )

        assertThat(
            bingoSystem.lastWinningBoard()
        ).isEqualTo(
            bingoSystem.blankBoards.second()
                .withAcceptedNumbers(bingoSystem.numbersDrawSequence)
        )
    }

    @Test
    fun `reads drawn numbers from input`() {
        assertThatSeq(
            bingoSystemFromInput("day04.input-sample").numbersDrawSequence
        ).containsExactlyElementsOf(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1"
                .split(',').map(String::toInt)
        )
    }

    @Test
    fun `reads bingo boards from input`() {
        assertThat(
            bingoSystemFromInput("day04.input-sample").blankBoards
        ).containsExactly(
            """22 13 17 11  0
                8  2 23  4 24
               21  9 14 16  7
                6 10  3 18  5
                1 12 20 15 19""".parseBingoBoard(),
            """ 3 15  0  2 22
                9 18 13 17  5
               19  8  7 25 23
               20 11 10 24  4
               14 21 16 12  6""".parseBingoBoard(),
            """14 21 17 24  4
               10 16 15  9 19
               18  8 23 26 20
               22 11 13  6  5
                2  0 12  3  7""".parseBingoBoard()
        )
    }

    @Test
    fun `sample input winning board is the following`() {
        assertThat(
            bingoSystemFromInput("day04.input-sample")
                .firstWinningBoard()
                ?.blankCopy()
        ).isEqualTo(
            """14 21 17 24  4
               10 16 15  9 19
               18  8 23 26 20
               22 11 13  6  5
                2  0 12  3  7""".parseBingoBoard()
        )
    }

    @Test
    fun `sample input winning board score is 4512`() {
        assertThat(
            bingoSystemFromInput("day04.input-sample")
                .firstWinningBoard()
                ?.score()
        ).isEqualTo(4512)
    }

    @Test
    fun `sample input loosing board score is 1924`() {
        assertThat(
            bingoSystemFromInput("day04.input-sample")
                .lastWinningBoard()
                ?.score()
        ).isEqualTo(1924)
    }
}