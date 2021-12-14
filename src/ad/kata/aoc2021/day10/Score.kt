package ad.kata.aoc2021.day10

@JvmInline
value class Score(val points: Long) : Comparable<Score> {

    override fun compareTo(other: Score) = points.compareTo(other.points)

    companion object {
        val Zero = Score(0)
    }
}