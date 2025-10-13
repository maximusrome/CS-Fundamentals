// -----------------------------------------------------------------
// Homework 2, Problem 4
// -----------------------------------------------------------------

// Now it's time to write your first full, if not a bit silly, app!
//
// TODO 1/1: Finish designing the program loopSong, using
//           reactConsole, that prints...
//
//           A-B-C
//
//           then...
//
//           Easy as 1-2-3
//
//           repeatedly until the user types "done" in response
//           to "Easy as 1-2-3", at which point the program
//           prints...
//
//           Baby you and me girl!
//
//           and returns the number of times the song looped
//           (meaning, how many times it printed "Easy as 1-2-3").
//
//           To help, you have an enumeration that represents the
//           beginning of the song, and each subsequent situation.
//           You also have tests - you just need to finish
//           loopSong, as well as designing the helper functions
//           that handle each type of event.
//
//           Suggestions:
//           - First, design how you will represent the state of
//             your program; SongState is useful, but you'll also
//             need to keep track of how many times the song looped
//             (hmm... more than piece of data in a single value,
//             what helps us there?).
//           - Next, have loopSong call reactConsole, providing
//             the initial state (ideally an example), and a set
//             of yet-to-be-designed helper functions, not
//             forgetting to return the number of song loops.
//           - Now, figure out the parameter/return types of each
//             helper, based upon how reactConsole works with your
//             data design.
//           - Then, write some tests to capture how these helper
//             functions *should* operate...
//             * songStateToText: should be producing the
//                                appropriate lyrics based on the
//                                situation (irrespective of loop
//                                count).
//             * nextSongState: should be figuring out what is the
//                              next situation based upon the
//                              current situation and what was
//                              typed (which matters only when
//                              "Easy as 1-2-3" was printed).
//             * isDone: should only return true when the current
//                       situation is that the song is done!
//            - And lastly, write the code for your helpers (as
//              informed by the types they use and tests!). You
//              might do these last two steps for each helper
//              individually; you can then test piece-by-piece if
//              you comment-out our tests and loopSong.
//
//           As with any interactive program, feel free to debug
//           by directly calling your program in main!
//

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.reactConsole
import khoury.runEnabledTests
import khoury.testSame

val LYRICS_ABC = "A-B-C"
val LYRICS_123 = "Easy as 1-2-3"
val LYRICS_DONE = "Baby you and me girl!"

enum class SongState {
    START,
    ABC,
    EASY123,
    DONE,
}

data class SongProgress(val state: SongState, val loopCount: Int)

fun songStateToText(progress: SongProgress): String {
    // Converts the current song progress state to its corresponding lyrics.
    return when (progress.state) {
        SongState.ABC -> LYRICS_ABC
        SongState.EASY123 -> LYRICS_123
        else -> LYRICS_DONE // This covers both the `START` and `DONE` states.
    }
}

fun nextSongState(
    progress: SongProgress,
    input: String,
): SongProgress {
    // Determines the next song state based on the current progress and user input.
    return when (progress.state) {
        SongState.ABC -> SongProgress(SongState.EASY123, progress.loopCount)
        SongState.EASY123 ->
            if (input == "done") {
                SongProgress(SongState.DONE, progress.loopCount + 1)
            } else {
                SongProgress(SongState.ABC, progress.loopCount + 1)
            }
        else -> progress // This covers both the `START` and `DONE` states, which should remain unchanged.
    }
}

fun isDone(progress: SongProgress): Boolean {
    // Checks if the current song progress state indicates the song is done.
    return progress.state == SongState.DONE
}

fun loopSong(): Int {
    // Initializes the song loop, interactively presenting the lyrics until the user decides to stop.
    val initialState = SongProgress(SongState.ABC, 0)
    val finalState = reactConsole(initialState, ::songStateToText, ::nextSongState, ::isDone)
    return finalState.loopCount
}

@EnabledTest
fun testLoopSong() {
    testSame(
        captureResults(
            ::loopSong,
            "",
            "done",
        ),
        CapturedResult(
            1,
            LYRICS_ABC,
            LYRICS_123,
            LYRICS_DONE,
        ),
        "quick",
    )

    testSame(
        captureResults(
            ::loopSong,
            "a",
            "b",
            "done",
            "c",
            "d",
            "done",
        ),
        CapturedResult(
            3,
            LYRICS_ABC,
            LYRICS_123,
            LYRICS_ABC,
            LYRICS_123,
            LYRICS_ABC,
            LYRICS_123,
            LYRICS_DONE,
        ),
        "longer",
    )
}

fun main() {
    println(loopSong())
}

main()
runEnabledTests(this)
