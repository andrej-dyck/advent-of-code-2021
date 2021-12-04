package ad.kata.aoc2021.day03

fun main() {
    val diagnosticReport = diagnosticReportFromInput("day03.input")

    println("-- Binary Diagnostic --")
    /* part 3 */
    println("submarine power consumption: ${diagnosticReport.powerConsumption()}")
    println("live support rating: ${diagnosticReport.liveSupportRating()}")
}