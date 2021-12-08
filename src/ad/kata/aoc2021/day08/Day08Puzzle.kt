package ad.kata.aoc2021.day08

fun main() {
    val troubleshooting = troubleShootingFromInput("day08.input")

    println("-- Seven Segment Display Troubleshooting --")
    /* part 1 */
    val countOf1478 = troubleshooting.identifiedOutputDigits().count()
    println("Total count of {1,2,7,8} in output values: $countOf1478")
}