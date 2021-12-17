package ad.kata.aoc2021.day11

import ad.kata.aoc2021.assertThatSeq
import ad.kata.aoc2021.parseStringList
import ad.kata.aoc2021.types.Matrix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class OctopusesTest {

    @Test
    fun `in each subsequent tick, the energy level of an octopus rises by 1`() {
        assertThatSeq(
            Octopuses(energyLevelsOf("0"))
                .timeProjection()
                .take(9)
                .map { it.energyLevels }
        ).containsExactly(
            energyLevelsOf("1"),
            energyLevelsOf("2"),
            energyLevelsOf("3"),
            energyLevelsOf("4"),
            energyLevelsOf("5"),
            energyLevelsOf("6"),
            energyLevelsOf("7"),
            energyLevelsOf("8"),
            energyLevelsOf("9"),
        )
    }

    @Test
    fun `in each subsequent tick, the energy level of each octopus rises by 1`() {
        assertThatSeq(
            Octopuses(energyLevelsOf("01234", "43210"))
                .timeProjection()
                .take(5)
                .map { it.energyLevels }
        ).containsExactly(
            energyLevelsOf("12345", "54321"),
            energyLevelsOf("23456", "65432"),
            energyLevelsOf("34567", "76543"),
            energyLevelsOf("45678", "87654"),
            energyLevelsOf("56789", "98765"),
        )
    }

    @Test
    fun `an energy level of 9 releases a flash and drops back to 0`() {
        assertThat(
            Octopuses(energyLevelsOf("9")).nextEnergyLevels()
        ).isEqualTo(
            energyLevelsOf("0")
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[09]; [20]",
        "[19]; [30]",
        "[0,9]; [2,0]",
        "[000,090,000]; [222,202,222]",
        "[00000,00000,00900,00000,00000]; [11111,12221,12021,12221,11111]",
        delimiter = ';'
    )
    fun `each flash raises the energy level of adjacent octopuses by 1 respectively`(
        energyLevels: String,
        expectedEnergyLevels: String
    ) {
        assertThat(
            Octopuses(
                energyLevelsOf(energyLevels.parseStringList())
            ).nextEnergyLevels()
        ).isEqualTo(
            energyLevelsOf(expectedEnergyLevels.parseStringList())
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[89]; [00]",
        "[99]; [00]",
        "[8,9]; [0,0]",
        "[11111,19991,19191,19991,11111]; [34543,40004,50005,40004,34543]",
        delimiter = ';'
    )
    fun `when a flash raises the energy level of adjacent octopuses to more than 9 they flash as well`(
        energyLevels: String,
        expectedEnergyLevels: String
    ) {
        assertThat(
            Octopuses(
                energyLevelsOf(energyLevels.parseStringList())
            ).nextEnergyLevels()
        ).isEqualTo(
            energyLevelsOf(expectedEnergyLevels.parseStringList())
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[9880]; [0002]",
        "[000,898,000]; [343,000,343]",
        "[00000,88888,88988,88888,00000]; [34443,00000,00000,00000,34443]",
        delimiter = ';'
    )
    fun `propagates flashes through all adjacent octopuses`(
        energyLevels: String,
        expectedEnergyLevels: String
    ) {
        assertThat(
            Octopuses(
                energyLevelsOf(energyLevels.parseStringList())
            ).nextEnergyLevels()
        ).isEqualTo(
            energyLevelsOf(expectedEnergyLevels.parseStringList())
        )
    }

    @ParameterizedTest
    @CsvSource(
        "[0]; 1",
        "[1]; 0",
        "[9]; 0",
        "[0,9]; 1",
        "[9,0]; 1",
        "[0,0]; 2",
        "[09]; 1",
        "[90]; 1",
        "[00]; 2",
        "[00,00]; 4",
        "[90,01]; 2",
        "[11111,19991,19191,19991,11111]; 0",
        "[34543,40004,50005,40004,34543]; 9",
        delimiter = ';'
    )
    fun `can count flashes, evident by energy level 0, of octopuses`(
        energyLevels: String,
        expectedFlashes: Int
    ) {
        assertThat(
            Octopuses(
                energyLevelsOf(energyLevels.parseStringList())
            ).countFlashes()
        ).isEqualTo(
            expectedFlashes
        )
    }

    @Suppress("MaxLineLength")
    @ParameterizedTest
    @CsvSource(
        "[1]; 1; 0",
        "[1]; 10; 1",
        "[1]; 20; 2",
        "[99]; 1; 2",
        "[11111,19991,19191,19991,11111]; 2; 9",
        "[5483143223,2745854711,5264556173,6141336146,6357385478,4167524645,2176841721,6882881134,4846848554,5283751526]; 10; 204",
        "[5483143223,2745854711,5264556173,6141336146,6357385478,4167524645,2176841721,6882881134,4846848554,5283751526]; 100; 1656",
        delimiter = ';'
    )
    fun `counts total amount of flashes after N steps`(
        energyLevels: String,
        steps: Int,
        expectedFlashes: Int
    ) {
        assertThat(
            Octopuses(
                energyLevelsOf(energyLevels.parseStringList())
            ).totalFlashesAfter(steps)
        ).isEqualTo(
            expectedFlashes
        )
    }

    @Test
    fun `reads octopuses energy levels from input`() {
        assertThat(
            octopusesFromInput("day11.input-sample").energyLevels
        ).isEqualTo(
            energyLevelsOf(
                "5483143223",
                "2745854711",
                "5264556173",
                "6141336146",
                "6357385478",
                "4167524645",
                "2176841721",
                "6882881134",
                "4846848554",
                "5283751526",
            )
        )
    }

    @Test
    fun `sample input energy levels progress in the following way`() {
        assertThatSeq(
            Octopuses(
                energyLevelsOf(
                    "5483143223",
                    "2745854711",
                    "5264556173",
                    "6141336146",
                    "6357385478",
                    "4167524645",
                    "2176841721",
                    "6882881134",
                    "4846848554",
                    "5283751526",
                )
            ).timeProjection().take(10).map { it.energyLevels }
        ).containsExactly(
            energyLevelsOf(
                "6594254334",
                "3856965822",
                "6375667284",
                "7252447257",
                "7468496589",
                "5278635756",
                "3287952832",
                "7993992245",
                "5957959665",
                "6394862637",
            ),
            energyLevelsOf(
                "8807476555",
                "5089087054",
                "8597889608",
                "8485769600",
                "8700908800",
                "6600088989",
                "6800005943",
                "0000007456",
                "9000000876",
                "8700006848",
            ),
            energyLevelsOf(
                "0050900866",
                "8500800575",
                "9900000039",
                "9700000041",
                "9935080063",
                "7712300000",
                "7911250009",
                "2211130000",
                "0421125000",
                "0021119000",
            ),
            energyLevelsOf(
                "2263031977",
                "0923031697",
                "0032221150",
                "0041111163",
                "0076191174",
                "0053411122",
                "0042361120",
                "5532241122",
                "1532247211",
                "1132230211",
            ),
            energyLevelsOf(
                "4484144000",
                "2044144000",
                "2253333493",
                "1152333274",
                "1187303285",
                "1164633233",
                "1153472231",
                "6643352233",
                "2643358322",
                "2243341322",
            ),
            energyLevelsOf(
                "5595255111",
                "3155255222",
                "3364444605",
                "2263444496",
                "2298414396",
                "2275744344",
                "2264583342",
                "7754463344",
                "3754469433",
                "3354452433",
            ),
            energyLevelsOf(
                "6707366222",
                "4377366333",
                "4475555827",
                "3496655709",
                "3500625609",
                "3509955566",
                "3486694453",
                "8865585555",
                "4865580644",
                "4465574644",
            ),
            energyLevelsOf(
                "7818477333",
                "5488477444",
                "5697666949",
                "4608766830",
                "4734946730",
                "4740097688",
                "6900007564",
                "0000009666",
                "8000004755",
                "6800007755",
            ),
            energyLevelsOf(
                "9060000644",
                "7800000976",
                "6900000080",
                "5840000082",
                "5858000093",
                "6962400000",
                "8021250009",
                "2221130009",
                "9111128097",
                "7911119976",
            ),
            energyLevelsOf(
                "0481112976",
                "0031112009",
                "0041112504",
                "0081111406",
                "0099111306",
                "0093511233",
                "0442361130",
                "5532252350",
                "0532250600",
                "0032240000",
            ),
        )
    }
}

fun energyLevelsOf(vararg rows: String) = energyLevelsOf(rows.toList())

fun energyLevelsOf(rows: List<String>) = Matrix(
    rows.map { it.toCharArray().map(Char::digitToInt) }
        .map { it.map { e -> EnergyLevel(e) } }
)

internal fun Octopuses.nextEnergyLevels() = timeProjection().first().energyLevels