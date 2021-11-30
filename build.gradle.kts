plugins {
    kotlin("jvm") version "1.6.0"
    id("io.gitlab.arturbosch.detekt").version("1.18.1")
}

group = "%GROUP%"
version = "1.0"

repositories {
    mavenLocal()
    mavenCentral()
    // Detekt needs kotlinx-html for its report
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

dependencies {
    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.8.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    // AssertJ
    testImplementation("org.assertj:assertj-core:3.21.0")
    // jqwik
    testImplementation("net.jqwik:jqwik:1.6.0")
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