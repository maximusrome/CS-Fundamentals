// -----------------------------------------------------------------
// Homework 7, Problem 4
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice applying the functional
// version of the accumulator pattern to reimplement the useful
// `joinToString` function.

// TODO 1/2: Finish designing the myJoinToString function, which
//           creates a string from all the elements of the supplied
//           list separated using a supplied separator.
//
//           Notes:
//           - For purposes of this problem, you must apply the
//             recursive form of the accumulator pattern.
//           - Recall that we can recur on the structure of a
//             Kotlin list by checking if it's empty and if not,
//             combine the first element with the recursive result
//             of the remaining list elements.
//           - You have been supplied tests that should pass once
//             the function has been completed.
//

// Recursively concatenates the elements of a list into a string with separators.
fun <T> myJoinToString(
    list: List<T>,
    separator: String,
    acc: String = "",
): String {
    if (list.isEmpty()) return acc.dropLast(separator.length)
    return myJoinToString(list.drop(1), separator, acc + list.first() + separator)
}

@EnabledTest
fun testMyJoinToString() {
    fun <T> helpTest(
        l: List<T>,
        sep: String,
        desc: String,
    ) {
        testSame(
            myJoinToString(l, sep),
            l.joinToString(sep),
            desc,
        )
    }

    helpTest(emptyList<Int>(), ", ", "empty")
    helpTest(listOf(1), ", ", "single")
    helpTest(listOf(1, 2, 3), ", ", "nums")
    helpTest(listOf("alice", "bob", "chris", "dan"), "-", "txts")
}

// TODO 2/2: Now given that your recursive implementation works,
//           finish designing the function myJoinToStringFold to
//           produce the same result, but effectively using the
//           fold abstraction (which iterates for itself).
//
//           You have been supplied tests that should pass once
//           the function has been completed; this time, instead
//           of comparing against the built-in Kotlin version, they
//           show that your two implementations solve the same
//           problem!
//

// Concatenates the elements of a list into a string with separators using fold.
fun <T> myJoinToStringFold(
    list: List<T>,
    separator: String,
): String {
    return list.foldIndexed("") { index, acc, elem ->
        if (index != list.size - 1) {
            acc + elem + separator
        } else {
            acc + elem
        }
    }
}

@EnabledTest
fun testMyJoinToStringFold() {
    fun <T> helpTest(
        l: List<T>,
        sep: String,
        desc: String,
    ) {
        testSame(
            myJoinToString(l, sep),
            myJoinToStringFold(l, sep),
            desc,
        )
    }

    helpTest(emptyList<Int>(), ", ", "empty")
    helpTest(listOf(1), ", ", "single")
    helpTest(listOf(1, 2, 3), ", ", "nums")
    helpTest(listOf("alice", "bob", "chris", "dan"), "-", "txts")
}

runEnabledTests(this)
