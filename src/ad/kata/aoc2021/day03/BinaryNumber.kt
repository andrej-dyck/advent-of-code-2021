package ad.kata.aoc2021.day03

data class BinaryNumber(private val digits: String) {

    init {
        require(digits.matches(binaryNumberRegex))
    }

    internal constructor(digits: List<Int>) : this(
        digits.joinToString("", transform = Int::toString)
    )

    fun size() = digits.length

    fun toInt() = digits.toInt(2)
    fun toDigitsAsInts() = digits.map { if (it == '1') 1 else 0 }

    fun inv() = BinaryNumber(
        digits.map { if (it == '1') '0' else '1' }.joinToString("")
    )

    override fun toString() = digits
}

private val binaryNumberRegex = """[01]+""".toRegex()