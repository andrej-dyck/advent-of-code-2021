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

fun Paper.formattedString(width: Int = 1): String {
    val xs = dots.maxOfOrNull { it.x } ?: 0
    val ys = dots.maxOfOrNull { it.y } ?: 0

    return (0..ys).joinToString("\n") { y ->
        (0..xs).joinToString("") { x ->
            if (Dot(x, y) in dots) "#".repeat(width) else ".".repeat(width)
        }
    }
}