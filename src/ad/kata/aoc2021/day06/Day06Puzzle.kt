package ad.kata.aoc2021.day06

fun main() {
    val lanternfishPopulation = lanternfishPopulationFromInput("day06.input")

    println("-- Lanternfish --")
    /* part 1 */
    val populationAfter80Days = lanternfishPopulation.afterDays(80)
    println("Lanternfish population after 80 days: ${populationAfter80Days.totalAmount()}")
}
