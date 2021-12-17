package ad.kata.aoc2021.day09

import ad.kata.aoc2021.types.Coordinate

fun Heightmap.basins() =
    lowPoints().map { locateBasinAt(it.key, it.value) }

private fun Heightmap.locateBasinAt(coordinate: Coordinate, height: Height) =
    locateBasin(mapOf(coordinate to height))

private fun Heightmap.locateBasin(
    basin: Basin,
    checkedLocations: Set<Coordinate> = emptySet()
): Basin =
    when (val l = basin.firstEntryOrNull { it.key !in checkedLocations }) {
        null -> basin
        else -> {
            val basinNeighbors = neighborsOf(l.key)
                .filterValues { l.value <= it && it < Height(9) }
                .filterKeys { it !in basin }

            locateBasin(basin + basinNeighbors, checkedLocations + l.key)
        }
    }

private inline fun <K, V> Map<K, V>.firstEntryOrNull(predicate: (Map.Entry<K, V>) -> Boolean) =
    entries.firstOrNull(predicate)

fun List<Basin>.takeLargest(n: Int) =
    asSequence().sortedByDescending { it.size }.take(n).toList()

typealias Basin = Map<Coordinate, Height>