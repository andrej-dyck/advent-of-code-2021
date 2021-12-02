package ad.kata.aoc2021.day02

internal fun String.parseMoveCommand() =
    moveCommandInputRegex.matchEntire(this)?.destructured
        ?.let { (direction, units) -> direction.toMoveCommand(units.toInt()) }
        ?: throw IllegalArgumentException("move command '$this' has wrong format. expected 'direction units'")

internal fun String.toMoveCommand(units: Int) = when (this) {
    "down" -> Down(units)
    "forward" -> Forward(units)
    "up" -> Up(units)
    else -> throw IllegalArgumentException("direction '$this' unknown")
}

private val moveCommandInputRegex = """([a-z]+) (\d+)""".toRegex()