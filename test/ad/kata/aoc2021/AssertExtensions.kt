package ad.kata.aoc2021

import org.assertj.core.api.Assertions
import org.assertj.core.api.IterableAssert

fun <T> assertThatSeq(actual: Sequence<T>): IterableAssert<T> =
    Assertions.assertThat(actual.asIterable())