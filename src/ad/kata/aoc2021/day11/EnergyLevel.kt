package ad.kata.aoc2021.day11

@JvmInline
value class EnergyLevel(val value: Int) {

    init {
        require(value in 0..9)
    }

    fun raiseBy(value: Int) = EnergyLevel(
        (this.value + value).takeUnless { it > 9 } ?: 0
    )

    fun hasFlashed() = value == 0

    override fun toString() = "$value"
}