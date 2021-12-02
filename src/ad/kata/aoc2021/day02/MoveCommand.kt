package ad.kata.aoc2021.day02

sealed interface MoveCommand {
    fun move(origin: MoveVector = MoveVector(0, 0)): MoveVector
}

data class Forward(private val units: Int) : MoveCommand {
    override fun move(origin: MoveVector) =
        origin.copy(horizontalPosition = origin.horizontalPosition + units)
}

data class Up(private val units: Int) : MoveCommand {
    override fun move(origin: MoveVector) =
        origin.copy(depth = origin.depth - units)
}

data class Down(private val units: Int) : MoveCommand {
    override fun move(origin: MoveVector) =
        origin.copy(depth = origin.depth + units)
}

data class MoveVector(val horizontalPosition: Int, val depth: Int)
