package ad.kata.aoc2021.day02

fun main() {
    val submarineCourse = submarineCourseFromInput("day02.input")

    println("-- Day 2: Dive! --")
    /* part 2 */
    val dest = submarineCourse.finalDestination()
    println("submarine course final destination: $dest (product = ${dest.horizontalPosition * dest.depth})")
}