package ad.kata.aoc2021.day13

fun main() {
    val origami = origamiFromInput("day13.input")

    println("-- Day 13: Transparent Origami --")
    /* part 1 */
    val paperAfter1stFold = origami.folds().first()
    println("Visible dots after first fold: ${paperAfter1stFold.dots.size}")
    /* part 2 */
    val finalFold = origami.finalFold()
    println("Final paper fold:\n${finalFold?.formattedString(width = 2)}")
}