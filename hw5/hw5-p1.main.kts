// -----------------------------------------------------------------
// Homework 5, Problem 1
// -----------------------------------------------------------------

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.input
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you'll practice selecting a loop type (either
// for, while, or do-while) for a task.

// TODO 1/1: Finish designing the function affirmTillDone that
//           outputs a supplied affirmation to the screen; after
//           each such output, prompt the user to type something
//           and end the program if they type a particular phrase,
//           otherwise output the affirmation again!
//
//           You have been supplied tests that should pass once the
//           function has been completed.
//
//           Note: since we are practicing with loops, you should
//                 NOT use either reactConsole or list abstractions
//                 (as a reminder, the input function in the Khoury
//                 library can help you get keyboard input).
//

val exitResponse = "exit"
val prompt = "Press '$exitResponse' to indicate you are done :)"
val finished = "Have a good day!"

// Repeatedly outputs the given affirmation until the user inputs the exit response.
fun affirmTillDone(affirmation: String) {
    do {
        println("$affirmation\n\n$prompt")
    } while (khoury.input() != exitResponse)
    println(finished)
}

@EnabledTest
fun testAffirmTillDone() {
    // Brené Brown
    val affirmation1 = "Courage starts with showing up and letting ourselves be seen."
    val helpTest1 = { affirmTillDone(affirmation1) }

    // Michelle Obama
    val affirmation2 = "Am I good enough? Yes I am."
    val helpTest2 = { affirmTillDone(affirmation2) }

    testSame(
        captureResults(
            helpTest1,
            "again",
            "AGAIN",
            exitResponse,
        ),
        CapturedResult(
            Unit,
            affirmation1, "", prompt,
            affirmation1, "", prompt,
            affirmation1, "", prompt,
            finished,
        ),
        "one",
    )

    testSame(
        captureResults(
            helpTest2,
            "MORE",
            exitResponse,
        ),
        CapturedResult(
            Unit,
            affirmation2,
            "",
            prompt,
            affirmation2,
            "",
            prompt,
            finished,
        ),
        "two",
    )
}

runEnabledTests(this)
