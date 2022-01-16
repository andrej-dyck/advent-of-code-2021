plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jetbrains.kotlinx.kover") version "0.5.0-RC2"
}

group = "ad.kata.aoc2021"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    // AssertJ
    testImplementation("org.assertj:assertj-core:3.22.0")
    // jqwik
    testImplementation("net.jqwik:jqwik:1.6.2")
}

/* Source sets by Kotlin conventions /src and /test */
val sources = setOf("main" to "src/", "test" to "test/")
kotlin {
    sources.forEach { (set, dir) ->
        sourceSets[set].apply { kotlin.srcDirs(dir) }
    }
}

/* Resources */
sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("test-resources")

/* Create Puzzle dirs and files */
tasks.register("prepPuzzle") {
    doLast {
        val dayNumber = project.property("day")?.toString()
            ?: throw GradleException("provide the puzzle day via -Pday=08")

        mkdir("src/ad/kata/aoc2021/day$dayNumber")
        mkdir("test/ad/kata/aoc2021/day$dayNumber")
        file("src/ad/kata/aoc2021/day$dayNumber/Day${dayNumber}Puzzle.kt").writeText(
            """
            package ad.kata.aoc2021.day$dayNumber

            fun main() {
                println("-- Day $dayNumber: Puzzle --")
                /* part 1 */
                println("TODO")
            }
        """.trimIndent()
        )
        file("resources/day$dayNumber.input").writeText("")
        file("resources/day$dayNumber.input-sample").writeText("")
    }
}

/* Detekt */
detekt {
    source = files(sources.map { it.second })
    config = files("detekt.yml")
}

/* Check with JUnit 5 and jqwik */
tasks.test {
    useJUnitPlatform {
        includeEngines("junit-jupiter", "jqwik")
        excludeEngines("junit-vintage")
    }
}

/* Gradle wrapper */
tasks.withType<Wrapper> {
    gradleVersion = "7.3"
    distributionType = Wrapper.DistributionType.BIN
}