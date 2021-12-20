package ad.kata.aoc2021.day03

fun main() {
    val diagnosticReport = diagnosticReportFromInput("day03.input")

    println("-- Day 3: Binary Diagnostic --")
    /* part 3 */
    println("submarine power consumption: ${diagnosticReport.powerConsumption()}")
    println("live support rating: ${diagnosticReport.liveSupportRating()}")
}