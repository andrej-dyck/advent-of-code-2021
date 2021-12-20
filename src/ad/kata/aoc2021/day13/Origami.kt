package ad.kata.aoc2021.day13

import ad.kata.aoc2021.PuzzleInput
import ad.kata.aoc2021.nonEmptyLines

class Origami(val paper: Paper, val instructions: Sequence<FoldInstruction>) {

    fun folds() = instructions.runningFold(paper) { p, instruction -> p.fold(instruction) }.drop(1)
}

fun Origami.finalFold() = folds().lastOrNull()

fun origamiFromInput(filename: String): Origami =
    PuzzleInput(filename).nonEmptyLines()
        .partition { it.startsWith("fold along") }
        .let { (instructions, dots) ->
            Origami(
                Paper(dots.map { it.toDot() }),
                instructions.mapNotNull { FoldInstruction.parseString(it) }.asSequence()
            )
        }