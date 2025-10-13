// -----------------------------------------------------------------
// Homework 5, Problem 2
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice implementing one of the built-
// in list abstractions using a different abstraction, along with
// your new lambda super-powers :)

// TODO 1/1: Finish designing the function myMap that uses the list
//           constructor (along with a lambda) to do the same work
//           that list.map would do.
//
//           You have been supplied tests that should pass once the
//           function has been completed, showing that your
//           function does exactly what the built-in map does!
//
//           Note: you are *required* to return the result of the
//                 list constructor with a lambda initialization
//                 function.
//

// Transforms each element in the inputList using the given lambda function and returns a new list.
fun <T, R> myMap(
    inputList: List<T>,
    transform: (T) -> R,
): List<R> {
    return List(inputList.size) { index -> transform(inputList[index]) }
}

@EnabledTest
fun testMyMap() {
    val emptyNums = emptyList<Int>()
    val nums = listOf(8, 6, 7, 5, 3, 0, 9)
    val names = listOf("alice", "bob", "chris")

    val add1: (Int) -> Int = { it + 1 }

    fun <T> exclaim(arg: T): String = "$arg!"
    val stringLen: (String) -> Int = { it.length }

    testSame(
        myMap(emptyNums, add1),
        emptyNums.map(add1),
        "empty, +1",
    )

    testSame(
        myMap(nums, add1),
        nums.map(add1),
        "non-empty, +1",
    )

    testSame(
        myMap(names, ::exclaim),
        names.map(::exclaim),
        "non-empty, exclaim",
    )

    testSame(
        myMap(names, stringLen),
        names.map(stringLen),
        "non-empty, length",
    )

    testSame(
        myMap(myMap(names, stringLen), add1),
        names.map(stringLen).map(add1),
        "non-empty, length->add1",
    )
}

runEnabledTests(this)
