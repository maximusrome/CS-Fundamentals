// -----------------------------------------------------------------
// Homework 7, Problem 1
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice applying list abstractions in
// order to design a useful algorithm, topK!

// When working with data, it is often helpful to be able to get
// the "best" set of data points, by some measure. For example...
//
// a) The single longest string in a list of strings
// b) The 2 smallest numbers in a list of integers
// c) The 5 points that are closest to the y-axis
//    (i.e., the absolute value of their x-coordinate
//    is smallest).
//
// To start, consider the following definition of an "evaluation"
// function: one that takes an input of some type and associates
// an output "score" (where bigger scores are understood to be
// better for the task at hand):

// a way to "score" a particular type of data
typealias EvaluationFunction<T> = (T) -> Int

// TODO 1/1: Design the function topK that takes a list of
//           items, a corresponding evaluation function, and k
//           (assumed to be a postive integer), and then returns
//           the k items in the list that get the highest score
//           (if there are ties, you are free to return any of the
//           winners; if there aren't enough items in the list,
//           return as many as you can).
//
//           To help...
//           -  Your tests must cover the three examples above,
//              each time covering the empty list, a list whose
//              size is at larger than the indicated k, and a
//              non-empty list whose size is smaller than k (this
//              last test isn't necessary for (a)). Here's a data
//              type for example (c):

// A two-dimensional point
data class Point2D(val x: Int, val y: Int) {
    // distance to the y-axis
    fun distToYAxis(): Int = if (x > 0) x else -x
}

val p2dOrigin = Point2D(0, 0)
val p2DRight = Point2D(3, -4)
val p2DLeft = Point2D(-10, 7)

@EnabledTest
fun testPoint2D() {
    testSame(
        p2dOrigin.distToYAxis(),
        0,
        "origin/distance",
    )

    testSame(
        p2DRight.distToYAxis(),
        3,
        "right/distance",
    )

    testSame(
        p2DLeft.distToYAxis(),
        10,
        "left/distance",
    )
}
//           - Here is a set of steps you are encouraged to follow
//             (using appropriate abstractions) in order to code up
//             your function:
//
//             1. Given your list of items, produce a
//                list of each item with its score (as given
//                by the evaluation function); here's a useful
//                type to capture that pairing:

// an association between an item and a score
data class ItemScore<T>(val item: T, val score: Int)

//             2. Sort the list of these pairs, biggest-first
//                (the sortedByDescending member function might
//                be useful here).
//
//             3. Now that you have the list in order, you no
//                longer need the scores; produce a list that
//                maintains this order, but just contains the
//                items.
//
//             4. Finally, just return the first k items of this
//                list (cough, that you "take").
//

// Sorts items by a given evaluation function and returns the top k items.
fun <T> topK(
    items: List<T>,
    evalFn: EvaluationFunction<T>,
    k: Int,
): List<T> {
    return items
        .map { ItemScore(it, evalFn(it)) }
        .sortedByDescending { it.score }
        .map { it.item }
        .take(k)
}

@EnabledTest
fun testTopKFunction() {
    val strings = listOf("apple", "banana", "cherry", "date")
    val integers = listOf(5, 3, 9, 1, 3)
    val points = listOf(Point2D(3, 4), Point2D(-1, 2), Point2D(0, -5), Point2D(2, 1))

    val longestString: EvaluationFunction<String> = { it.length }
    val smallestNumber: EvaluationFunction<Int> = { -it }
    val closestToYAxis: EvaluationFunction<Point2D> = { -it.distToYAxis() }

    testSame(
        topK(strings, longestString, 2),
        listOf("banana", "cherry"),
        "Top 2 longest strings",
    )
    testSame(
        topK(strings, longestString, 5),
        strings.sortedByDescending { it.length },
        "Top 5 longest strings in 4 item list",
    )
    testSame(
        topK(emptyList<String>(), longestString, 1),
        listOf<String>(),
        "Empty list of strings",
    )

    testSame(
        topK(integers, smallestNumber, 2),
        listOf(1, 3),
        "Top 2 smallest integers",
    )
    testSame(
        topK(integers, smallestNumber, 10),
        integers.sorted(),
        "Top 10 smallest integers in 5 item list with ties",
    )
    testSame(
        topK(emptyList<Int>(), smallestNumber, 3),
        listOf<Int>(),
        "Empty list of integers",
    )

    testSame(
        topK(points, closestToYAxis, 3),
        listOf(
            Point2D(0, -5),
            Point2D(-1, 2),
            Point2D(2, 1),
        ),
        "Top 3 points closest to Y-axis",
    )
    testSame(
        topK(points, closestToYAxis, 5),
        points.sortedBy { it.distToYAxis() },
        "Top 5 closest points in 4 item list",
    )
    testSame(
        topK(emptyList<Point2D>(), closestToYAxis, 2),
        listOf<Point2D>(),
        "Empty list of Point2D",
    )
}

runEnabledTests(this)
