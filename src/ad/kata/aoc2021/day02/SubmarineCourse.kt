package ad.kata.aoc2021.day02

import ad.kata.aoc2021.PuzzleInput

class SubmarineCourse(val commands: Sequence<MoveCommand>) {
    fun finalDestination() =
        commands.fold(MoveVector(0, 0)) { mv, c -> c.move(mv) }
}

fun submarineCourseFromInput(filename: String) = SubmarineCourse(
    PuzzleInput(filename).lines().map(String::parseMoveCommand)
)