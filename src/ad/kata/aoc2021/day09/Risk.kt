package ad.kata.aoc2021.day09

@JvmInline
value class Risk(val level: Int)

inline fun <T> List<T>.assessRiskLevels(assess: (T) -> Risk) = map(assess)
fun List<Risk>.total() = Risk(sumOf { it.level })