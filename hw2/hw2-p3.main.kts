// -----------------------------------------------------------------
// Homework 2, Problem 3
// -----------------------------------------------------------------

// We are making nametags for a (magical) student event, and want
// to make sure they have enough space for all the letters in each
// name. We are also formatting the nametags so that they have a
// consistent format: Last, First Middle - this way the teachers
// can accurately praise/critique their students.
//
// TODO 1/1: Given the data types and examples below, design the
//           function numCharsNeeded that takes a magical pair and
//           returns the number of characters that would be
//           necessary to represent the longer student's formatted
//           name.
//
//           For instance, if Hermione and Ron are paired, her
//           formatted name is "Granger, Hermione Jean" (22
//           characters) and his is "Weasley, Ron Bilius" (19
//           characters), and so the function should return 22.
//
//           Note the nested data types, and so duplicated work -
//           let type-driven development lead you to an effective
//           decomposition of well-designed functions :)
//

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// represents a person's first/middle/last names
data class Name(val first: String, val middle: String, val last: String)

val HJP = Name("Harry", "James", "Potter")
val HJG = Name("Hermione", "Jean", "Granger")
val RBW = Name("Ron", "Bilius", "Weasley")

// represents a pairing of two names
data class MagicPair(val p1: Name, val p2: Name)

val MAGIC_HARRY_RON = MagicPair(HJP, RBW)
val MAGIC_HARRY_HERMIONE = MagicPair(HJP, HJG)
val MAGIC_HERMIONE_RON = MagicPair(HJG, RBW)

fun formattedNameLength(name: Name): Int {
    // helper function to get the formatted name length
    return name.last.length + 2 + name.first.length + 1 + name.middle.length
}

fun numCharsNeeded(pair: MagicPair): Int {
    // returns number of characters of the longer student's name
    return maxOf(formattedNameLength(pair.p1), formattedNameLength(pair.p2))
}

@EnabledTest
fun testNumCharsNeeded() {
    // Testing numCharsNeeded function
    fun testHelp(
        pair: MagicPair,
        expected: Int,
        description: String,
    ) {
        testSame(
            numCharsNeeded(pair),
            expected,
            description,
        )
    }

    testHelp(
        MAGIC_HARRY_RON,
        19,
        "Harry paired with Ron",
    )
    testHelp(
        MAGIC_HARRY_HERMIONE,
        22,
        "Harry paired with Hermione",
    )
    testHelp(
        MAGIC_HERMIONE_RON,
        22,
        "Hermione paired with Ron",
    )
}

fun main() {
}

runEnabledTests(this)
main()
