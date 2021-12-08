# Advent of Code 2021

![build](https://github.com/andrej-dyck/advent-of-code-2021/actions/workflows/gradle-ci.yml/badge.svg?branch=main)

> [Advent of Code](https://adventofcode.com) is an Advent calendar of small programming puzzles for a variety of skill sets and skill levels that can be solved in any programming language you like.

In this repository, the code tackles the [2021](https://adventofcode.com/2021) puzzles. 

## Prepare a puzzle with Gradle
```
./gradlew prepPuzzle -Pday=01
```
This task will create a package `day01` in `src` and `test`, as well as create two files `day01.input` and `day01.input-sample` in `resources`. Finally, it will create a `Day01Puzzle.kt` in the `src` package.

## Build with Gradle
```
./gradlew build
```

#### Only Assemble
```
./gradlew assemble
```
#### Only Detekt
```
./gradlew detekt
```
#### Only Tests
```
./gradlew test
```
