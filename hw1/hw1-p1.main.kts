// -----------------------------------------------------------------
// Homework 1, Problem 1
// -----------------------------------------------------------------

// In a math class, the perfect squares are the squares of the
// whole numbers: 1, 4, 9, 16, ... (since 1²=1, 2²=4, 3²=9, 4²=16).
//
// Given the above description, we'd commonly say that the "first"
// perfect square is 1, the second is 4, etc. However, as we'll
// talk about later in this class, computer scientists commonly
// start counting at 0 (like a European building or elevator!).
//
// So given this context...

// TODO 1/3: Write the function kthPerfectSquare, which accepts
//           a number and outputs that perfect square. For
//           example, if the supplied number was 0, the function
//           should return 1; if the function was supplied 1, it
//           should return 4.
//
//           You can assume that the supplied number is
//           non-negative. As discussed in class, try to avoid
//           duplicating work in your code, and make sure to have
//           a short comment before the function describing what
//           it does :)
//

fun kthPerfectSquare(n: Int): Int {
    // outputs next perfect square
    return (n + 1) * (n + 1)
}

// TODO 2/3: Write the function perfectDescription that accepts a
//           number and provides a textual description like the
//           following...
//
//           perfectDescription(0) -> "perfect square 0 is 1"
//           perfectDescription(1) -> "perfect square 1 is 4"
//
//           Of course, make use of kthPerfectSquare, since it'll
//           be quite useful!
//

fun perfectDescription(n: Int): String {
    // output the textual description for a given number
    return "perfect square $n is ${kthPerfectSquare(n)}"
}

// TODO 3/3: In your main function, output to the screen some
//           uses of each of the above functions so that you have
//           confidence it works!
//
//           Note: you'll find it repetitive to write similar code
//                 for this purpose, and so we provided an
//                 approach of having a function to avoid some of
//                 the similar pieces in a helper test function -
//                 just uncomment, and feel free to adapt for
//                 other parts of the assignment! (Even this feels
//                 a bit repetitive, so stay tuned for more in the
//                 upcoming weeks!)

fun main() {
    // outputs inputs/outputs/expectations for kthPerfectSquare
    fun testSquare(
        k: Int,
        expectedResult: Int,
    ) {
        println("tried $k, got ${ kthPerfectSquare(k) }, expected $expectedResult")
    }

    // outputs inputs/outputs/expectations for perfectDescription
    fun testPerfectDescription(
        k: Int,
        expectedResult: String,
    ) {
        println("tried $k, got \"${ perfectDescription(k) }\", expected \"$expectedResult\"")
    }

    println("== kthPerfectSquare ==")
    testSquare(0, 1)
    testSquare(1, 4)
    testSquare(2, 9)
    testSquare(3, 16)
    println()

    println("\n== perfectDescription ==")
    testPerfectDescription(0, "perfect square 0 is 1")
    testPerfectDescription(1, "perfect square 1 is 4")
    testPerfectDescription(2, "perfect square 2 is 9")
    testPerfectDescription(3, "perfect square 3 is 16")
}

main()
