package ad.kata.aoc2021.day08

@JvmInline
value class Digit(val value: Int) {

    init {
        require(value in 0..9)
    }
}

fun List<Digit>.toInt() = joinToString(separator = "") { "${it.value}" }.toInt()