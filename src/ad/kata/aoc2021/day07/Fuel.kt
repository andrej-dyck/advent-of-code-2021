package ad.kata.aoc2021.day07

@JvmInline
value class Fuel(private val amount: Int) : Comparable<Fuel> {

    override fun compareTo(other: Fuel) = amount.compareTo(other.amount)
}