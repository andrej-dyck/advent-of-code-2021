package ad.kata.aoc2021

import ad.kata.aoc2021.extensions.csvLines
import ad.kata.aoc2021.extensions.csvLinesWithHeaders
import java.io.File

class PuzzleInput(private val input: File) {

    constructor(filename: String, directory: String = "resources") : this(File(directory, filename))

    fun lines(): Sequence<String> = sequence { input.useLines { yieldAll(it) } }
}

fun PuzzleInput.csvLines() = lines().csvLines()
fun PuzzleInput.csvLinesWithHeaders() = lines().csvLinesWithHeaders()