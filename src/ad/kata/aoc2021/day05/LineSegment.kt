package ad.kata.aoc2021.day05

import ad.kata.aoc2021.extensions.splitTrim
import kotlin.math.sign

data class LineSegment(val p: Point, val q: Point) {

    val direction by lazy {
        Vector(x = (q.x - p.x).sign, y = (q.y - p.y).sign)
    }

    fun points() =
        generateSequence(p) { it + direction }.takeWhile { it != q } + q
}

infix fun Point.to(q: Point) = LineSegment(p = this, q)

fun LineSegment.isHorizontal() = direction.y == 0
fun LineSegment.isVertical() = direction.x == 0

fun parseLineSegment(lineSegment: String) =
    lineSegment
        .splitTrim("->")
        .let { (p, q) -> p.toPoint() to q.toPoint() }

