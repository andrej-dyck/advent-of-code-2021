package ad.kata.aoc2021.day07

import kotlin.math.abs

@JvmInline
value class HorizontalPosition(val value: Int) : Comparable<HorizontalPosition> {

    fun absDistanceTo(other: HorizontalPosition) = abs(value - other.value)
    override fun compareTo(other: HorizontalPosition) = value.compareTo(other.value)
}

fun horizontalPositionsOf(positions: List<Int>) =
    positions.map { HorizontalPosition(it) }