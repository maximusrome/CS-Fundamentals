// -----------------------------------------------------------------
// Homework 6, Problem 1
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice with looping and mutation to
// implement a form of the functional `map` function to actually
// change the elements of a supplied list (instead of returning
// a new list).

// TODO 1/1: Design the function myInplaceMap, which transforms the
//           elements of a supplied list using a supplied function.
//
//           For example, if the function was supplied [1, 2, 3] as
//           a mutable list, as well as a function that doubles a
//           supplied number, the supplied list would be [2, 4, 6]
//           after the function completed (note that the function
//           itself should not return any value).
//
//           Make sure to sufficiently test this function,
//           including empty vs lists that have elements, as well
//           as differing types.
//

// Function which mutates the list in place using the provided transformation function.
fun <T> myInplaceMap(
    list: MutableList<T>,
    transform: (T) -> T,
) {
    for (index in list.indices) {
        list[index] = transform(list[index])
    }
}

@EnabledTest
fun testMyInplaceMap() {
    val intList = mutableListOf(1, 2, 3)
    myInplaceMap(intList) { it * 2 }
    testSame(
        intList,
        mutableListOf(2, 4, 6),
        "intList should be mutated into [2, 4, 6]",
    )
    val stringList = mutableListOf("a", "b", "c")
    myInplaceMap(stringList) { it + it }
    testSame(
        stringList,
        mutableListOf("aa", "bb", "cc"),
        "stringList should be mutated into ['aa', 'bb', 'cc']",
    )
    val emptyList = mutableListOf<Int>()
    myInplaceMap(emptyList) { it * 2 }
    testSame(
        emptyList,
        mutableListOf<Int>(),
        "emptyList should remain empty",
    )
    val mixedList = mutableListOf<Any>(1, "a", 2.5)
    myInplaceMap(mixedList) {
        when (it) {
            is Int -> it + 1
            is String -> it + it
            is Double -> it * 2
            else -> it
        }
    }
    testSame(
        mixedList,
        mutableListOf<Any>(2, "aa", 5.0),
        "mixedList should be mutated into [2, 'aa', 5.0]",
    )
}

runEnabledTests(this)
