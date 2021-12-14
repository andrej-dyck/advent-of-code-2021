package ad.kata.aoc2021.day10

class AutocompleteScore(private val program: NavigationProgram) {

    val scores by lazy { program.incompleteLines().map { it.score() }.toList() }

    private fun IncompleteLine.score() = Score(
        missingClosings.fold(0) { t, c -> t * 5 + (charPoints[c] ?: 0) }
    )

    companion object {
        private val charPoints = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    }
}

fun AutocompleteScore.winner(): Score? = scores.sorted().getOrNull(scores.size / 2)

