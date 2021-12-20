package ad.kata.aoc2021.day13

data class Paper(val dots: Set<Dot>) {

    constructor(dots: List<Dot>) : this(dots.toHashSet())
    constructor(vararg dots: Pair<Int, Int>) : this(dots.map { it.toDot() })
}

data class Dot(val x: Int, val y: Int) {
    override fun toString() = "$x,$y"
}

fun Pair<Int, Int>.toDot() = Dot(first, second)
fun String.toDot() = split(',').let { (x, y) -> Dot(x.toInt(), y.toInt()) }