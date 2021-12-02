package ad.kata.aoc2021.day02

fun main() {
    val submarineCourse = submarineCourseFromInput("day02.input")

    println("-- Submarine Course --")
    /* part 1 */
    val dest = submarineCourse.finalDestination()
    println("final destination: $dest (product = ${dest.horizontalPosition * dest.depth})")
}