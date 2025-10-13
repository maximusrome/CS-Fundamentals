// -----------------------------------------------------------------
// Homework 4, Problem 1
// -----------------------------------------------------------------

// In this problem we'll practice with imperative `for` loops.
// As such, your solutions should NOT use the list abstractions
// we learned last week.
//

// TODO 1/3: Finish designing the function myForEachDo that takes
//           a list of students, and a function that performs an
//           action given a student. Of course this means you need
//           to finish designing the data type Student, which
//           contains a student's first name, last name, and ID.
//
//           You have been supplied tests that should pass once
//           these steps have been completed - you should NOT
//           change the supplied code, which means the design
//           of your data type AND function must be driven by
//           the tests.
//

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.runEnabledTests
import khoury.testSame

// Student data class definition
data class Student(val first: String, val last: String, val id: Int)

// Implementing the myForEachDo function
fun myForEachDo(
    students: List<Student>,
    action: (Student) -> Unit,
) {
    for (student in students) {
        action(student)
    }
}

val studentAlice = Student("alice", "faythe", 1)
val studentBob = Student("bob", "mallory", 6)
val studentCarlos = Student("carlos", "sybil", 7)

// says howdy to a student
fun howdyStudent(student: Student) {
    println("Howdy, ${ student.first } ${ student.last } (${ student.id })!")
}

val howdyAlice = "Howdy, alice faythe (1)!"
val howdyBob = "Howdy, bob mallory (6)!"
val howdyCarlos = "Howdy, carlos sybil (7)!"

@EnabledTest
fun testHowdyStudent() {
    fun helpAlice() {
        howdyStudent(studentAlice)
    }

    fun helpBob() {
        howdyStudent(studentBob)
    }

    fun helpCarlos() {
        howdyStudent(studentCarlos)
    }

    testSame(
        captureResults(::helpAlice),
        CapturedResult(Unit, howdyAlice),
        "alice",
    )

    testSame(
        captureResults(::helpBob),
        CapturedResult(Unit, howdyBob),
        "bob",
    )

    testSame(
        captureResults(::helpCarlos),
        CapturedResult(Unit, howdyCarlos),
        "carlos",
    )
}

@EnabledTest
fun testForEachDo() {
    fun helpTest(students: List<Student>) {
        myForEachDo(students, ::howdyStudent)
    }

    fun helpTestAll() {
        helpTest(
            listOf(
                studentAlice,
                studentBob,
                studentCarlos,
            ),
        )
    }

    fun helpTestNone() {
        helpTest(emptyList<Student>())
    }

    testSame(
        captureResults(::helpTestNone),
        CapturedResult(Unit),
        "none",
    )

    testSame(
        captureResults(::helpTestAll),
        CapturedResult(
            Unit,
            howdyAlice,
            howdyBob,
            howdyCarlos,
        ),
        "all",
    )
}

// TODO 2/3: Finish designing the predicate isPalindrome that
//           determines if a supplied string has the same
//           characters front-to-back as back-to-front. Since
//           we are practicing use of for-loops, we want you to
//           do this in a very specific way, so here is the
//           pseudocode for the algorithm we have in-mind:
//
//           1. Loop through all the valid indices of the
//              string...
//
//              a) if the character at that index (let's call
//                 it `i` for the moment) is not the same as
//                 `i` away from the last index, then the word
//                 is not a palindrome.
//
//           2. If you get through the end of the word, it is a
//              valid palindrome!
//
//           You have been supplied tests (comparing to a simpler
//           way to solve this problem).
//
//           Notes:
//           - As with many sequences, strings have .indices and
//             .lastIndex that you'll find quite useful.
//           - Following a known approach to solving a problem is
//             fairly common when designing software; to read more
//             about the terms in the description...
//             * https://en.wikipedia.org/wiki/Algorithm
//             * https://en.wikipedia.org/wiki/Pseudocode
//

// Implementing the isPalindrome function using the provided pseudocode
fun isPalindrome(s: String): Boolean {
    for (i in s.indices) {
        if (s[i] != s[s.lastIndex - i]) {
            return false
        }
    }
    return true
}

@EnabledTest
fun testIsPalindrome() {
    // simpler way of solving the problem :)
    fun shouldBe(s: String): Boolean {
        return s == s.reversed()
    }

    fun helpTest(s: String) {
        testSame(
            isPalindrome(s),
            shouldBe(s),
            s,
        )
    }

    helpTest("howdy")
    helpTest("mom")
    helpTest("taco cat".replace(" ", ""))
    helpTest("hoot")
    helpTest("toot")
}

// TODO 3/3: Finish designing the function printTriangle that
//           makes some simple ASCII art...
//
//           https://en.wikipedia.org/wiki/ASCII_art
//
//           In particular, your function will accept a positive
//           integer and then print to the screen that large of
//           an isosceles right-triangle made of *'s.
//
//           For example, the first three such triangles...
//
//           *
//
//           *
//           **
//
//           *
//           **
//           ***
//
//           So you'll have as many lines as the supplied number,
//           and each line has an increasing number of *'s.
//
//           While you could construct a string, instead we want
//           you to apply "nested" for-loops: that is, a loop
//           within a loop. The outer loop should handle breaks
//           between lines (via println), whereas the inner loop
//           should use print to output the appropriate number of
//           *'s - you have tests to help!
//
//           And since you are crushing it, here's a flower...
//
//           @}-,-`-.
//

// Implementing the printTriangle function
fun printTriangle(n: Int) {
    for (i in 1..n) {
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}

@EnabledTest
fun testPrintTriangle() {
    fun helpTest1() {
        printTriangle(1)
    }

    fun helpTest2() {
        printTriangle(2)
    }

    fun helpTest3() {
        printTriangle(3)
    }

    fun helpTest10() {
        printTriangle(10)
    }

    testSame(
        captureResults(::helpTest1),
        CapturedResult(
            Unit,
            "*",
        ),
        "1",
    )

    testSame(
        captureResults(::helpTest2),
        CapturedResult(
            Unit,
            "*",
            "**",
        ),
        "2",
    )

    testSame(
        captureResults(::helpTest3),
        CapturedResult(
            Unit,
            "*",
            "**",
            "***",
        ),
        "3",
    )

    testSame(
        captureResults(::helpTest10),
        CapturedResult(
            Unit,
            "*",
            "**",
            "***",
            "****",
            "*****",
            "******",
            "*******",
            "********",
            "*********",
            "**********",
        ),
        "10",
    )
}

runEnabledTests(this)
