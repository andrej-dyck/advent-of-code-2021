package ad.kata.aoc2021.day05

import ad.kata.aoc2021.extensions.splitTrim

data class Point(val x: Int, val y: Int)
data class Vector(val x: Int, val y: Int)

internal operator fun Point.plus(vector: Vector) =
    copy(x = x + vector.x, y = y + vector.y)

internal fun String.toPoint() =
    removeSurrounding("(", ")")
        .splitTrim(',')
        .let { (x, y) -> Point(x.toInt(), y.toInt()) }
