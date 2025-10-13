// -----------------------------------------------------------------
// Homework 2, Problem 1
// -----------------------------------------------------------------

// TODO 1/1: Design the predicate startsWithY that determines if
//           the supplied string starts with the letter "y"
//           (either upper or lowercase).
//
//           Hints:
//            - The string.startsWith(prefix) function will help
//              evaluate the prefix (even if the string is too
//              short).
//            - The string.lowercase/uppercase() functions help
//              you not worry about case.
//            - Remember that "designing" a function means to
//              document and test it!
//

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

fun startsWithY(word: String): Boolean {
    // outputs boolean value based on if the string starts with "y" disregarding capitalization
    return word.lowercase().startsWith("y")
}

@EnabledTest
fun testStartsWithY() {
    // testing startsWithY function
    testSame(
        startsWithY("yellow"),
        true,
        "startsWithY starting with lowercase y",
    )
    testSame(
        startsWithY("Yellow"),
        true,
        "startsWithY starting with uppercase y",
    )
    testSame(
        startsWithY("ellow"),
        false,
        "startsWithY without y",
    )
    testSame(
        startsWithY("ellowy"),
        false,
        "startsWithY not starting with y",
    )
    testSame(
        startsWithY("y"),
        true,
        "startsWithY with only y",
    )
    testSame(
        startsWithY(""),
        false,
        "startsWithY empty",
    )
}

fun main() {
}

runEnabledTests(this)
main()
