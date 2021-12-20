package ad.kata.aoc2021.day13

sealed interface FoldInstruction {

    fun project(dot: Dot): Dot

    companion object {
        fun parseString(instruction: String) =
            foldInstructionRegex.matchEntire(instruction)
                ?.destructured
                ?.let { (direction, line) -> parseString(direction, line) }

        private fun parseString(direction: String, line: String) = when (direction) {
            "y" -> FoldUp(y = line.toInt())
            "x" -> FoldLeft(x = line.toInt())
            else -> null
        }

        private val foldInstructionRegex = """fold along ([xy])=(\d+)""".toRegex()
    }
}

data class FoldUp(val y: Int) : FoldInstruction {

    override fun project(dot: Dot) =
        if (dot.y < y) dot
        else if (dot.y == y) dot.copy(y = y - 1)
        else dot.copy(y = (-dot.y).mod(y))
}

data class FoldLeft(val x: Int) : FoldInstruction {

    override fun project(dot: Dot) =
        if (dot.x < x) dot
        else if (dot.x == x) dot.copy(x = x - 1)
        else dot.copy(x = (-dot.x).mod(x))
}

fun Paper.fold(instruction: FoldInstruction) = Paper(
    dots.map { instruction.project(it) }
)