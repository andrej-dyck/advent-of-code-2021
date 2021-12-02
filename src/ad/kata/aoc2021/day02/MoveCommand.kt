package ad.kata.aoc2021.day02

sealed interface MoveCommand {
    fun move(origin: SubmarinePosition): SubmarinePosition
}

data class Forward(private val units: Int) : MoveCommand {
    override fun move(origin: SubmarinePosition) = origin.copy(
        horizontalPosition = origin.horizontalPosition + units,
        depth = origin.depth + origin.aim * units
    )
}

data class Up(private val units: Int) : MoveCommand {
    override fun move(origin: SubmarinePosition) =
        origin.copy(aim = origin.aim - units)
}

data class Down(private val units: Int) : MoveCommand {
    override fun move(origin: SubmarinePosition) =
        origin.copy(aim = origin.aim + units)
}

data class SubmarinePosition(val horizontalPosition: Int = 0, val depth: Int = 0, val aim: Int = 0)
