// -----------------------------------------------------------------
// Homework 6, Problem 3
// -----------------------------------------------------------------

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.input
import khoury.runEnabledTests
import khoury.testSame

// In this problem you'll peel back the covers behind...
// reactConsole!!! You'll first implement the (functional) version
// you've been using, and then experience what an imperative
// version would feel like :)

// TODO 1/2: Use loops and mutation to implement funcReactConsole,
//           which works mostly like what you've been using all
//           semester in the Khoury library...
//
//           a) Start at the (supplied) initial state
//           b) Check if it is a terminal state (via the supplied
//              predicate); if not...
//              i.   Use the supplied rendering function to print to
//                   the screen the rendering of the current state
//                   as its own line.
//              ii.  Use the supplied transition function to change
//                   the current state.
//              iii. Done with this time through the loop!
//           c) Once a terminal state is achieved, use the
//              supplied terminal rendering function to print to
//              the screen the rendering of the terminal state as
//              its own line.
//           d) Return the terminal state.
//
//           Note: the real version uses default arguments, which
//                 we haven't covered, so this version *requires*
//                 that a terminal rendering function is supplied.
//
//           You have been supplied tests that should pass once the
//           function has been completed.
//
//           Note: while this is called funcReactConsole, and feels
//                 like a functional interface to the problem, you
//                 must implement it using loops and mutation!
//

// Functional React Console implementation
fun <State> funcReactConsole(
    initialState: State,
    stateToText: (State) -> String,
    nextState: (State, String) -> State,
    isTerminalState: (State) -> Boolean,
    terminalStateToText: (State) -> String,
): State {
    var currentState = initialState
    while (!isTerminalState(currentState)) {
        println(stateToText(currentState))
        currentState = nextState(currentState, readLine() ?: "")
    }
    println(terminalStateToText(currentState))
    return currentState
}

@EnabledTest
fun testFunReactConsole() {
    testSame(
        captureResults(
            {
                funcReactConsole(
                    initialState = 1,
                    stateToText = { s -> "$s" },
                    nextState = { s, _ -> 2 * s },
                    isTerminalState = { s -> s >= 100 },
                    terminalStateToText = { _ -> "fin." },
                )
            },
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ),
        CapturedResult(
            128,
            "1", "2", "4", "8", "16", "32", "64", "fin.",
        ),
        "doubling",
    )

    testSame(
        captureResults(
            {
                funcReactConsole(
                    initialState = "start",
                    stateToText = { s -> "$s!" },
                    nextState = { _, kbInput -> kbInput },
                    isTerminalState = { s -> s == "done" },
                    terminalStateToText = { s -> "$s!!!" },
                )
            },
            "howdy",
            "cool",
            "getting old",
            "done",
        ),
        CapturedResult(
            "done",
            "start!",
            "howdy!",
            "cool!",
            "getting old!",
            "done!!!",
        ),
        "done yet?",
    )
}

// TODO 2/2: NOW, we've provided an imperative implementation,
//           whereas you need to implement the classes necessary
//           for the associated tests (which are basically the
//           same as above) to run successfully.
//
//           Make sure to follow the best practices you've learned
//           for designing classes with mutable state (i.e., these
//           should not be data classes, mutable data should be
//           private, and of course you'll need to test them!).
//

// Represents a state that doubles its numeric value
// with each transition until a threshold is reached.
class DoublingState(private var num: Int) : IReactStateImp {
    // Renders the current state as text.
    override fun toText(): String = num.toString()

    // Doubles the numeric value on transition.
    override fun transition(kbInput: String) {
        num *= 2
    }

    // Checks if the current state is terminal (i.e., num >= 100).
    override fun isTerminal(): Boolean = num >= 100

    // Renders the terminal state as text.
    override fun terminalToText(): String = "fin."

    // Retrieves the current numeric value.
    fun getNum(): Int = num
}

// Represents a state that updates its phrase with user input
// until an exit phrase is entered.
class DoneYetState(private var phrase: String, private val exitPhrase: String) : IReactStateImp {
    // Renders the current phrase as text with an exclamation.
    override fun toText(): String = "$phrase!"

    // Updates the phrase based on user input.
    override fun transition(kbInput: String) {
        phrase = kbInput
    }

    // Checks if the current phrase matches the exit phrase.
    override fun isTerminal(): Boolean = phrase == exitPhrase

    // Renders the terminal phrase as text with triple exclamations.
    override fun terminalToText(): String = "$exitPhrase!!!"
}

// Interface defining the required member functions for states in
// the imperative react console.
interface IReactStateImp {
    // Renders the current state as text.
    fun toText(): String

    // Transitions the current state based on user input.
    fun transition(kbInput: String)

    // Checks if the state is in a terminal condition.
    fun isTerminal(): Boolean

    // Renders the terminal state as text.
    fun terminalToText(): String
}

// member functions necessary for a
// class to utilize the imperative
// reactConsole below
interface IReactStateImp {
    // render the state
    fun toText(): String

    // transition to the next state
    // based upon supplied kb input
    fun transition(kbInput: String)

    // terminal state predicate
    fun isTerminal(): Boolean

    // render terminal states
    fun terminalToText(): String
}

// implements reactConsole imperatively
// (using an object-oriented state)
fun impReactConsole(state: IReactStateImp) {
    while (!state.isTerminal()) {
        println(state.toText())

        state.transition(input())
    }
    println(state.terminalToText())
}

@EnabledTest
fun testImpReactConsole() {
    // creates the initial state
    // for the doubling program
    val doubleState = DoublingState(1)

    // tests (primarily) the printed program output
    testSame(
        captureResults(
            { impReactConsole(doubleState) },
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        ),
        CapturedResult(
            Unit,
            "1", "2", "4", "8", "16", "32", "64", "fin.",
        ),
        "doubling: I/O",
    )

    // tests (primarily) the final program state
    testSame(
        doubleState.getNum(),
        128,
        "doubling: final state",
    )

    // creates the initial state
    // for the done-yet program
    // (with both starting phrase
    // and phrase that signals
    // the end)
    val startPhrase = "start"
    val exitPhrase = "done"
    val doneYetState = DoneYetState(startPhrase, exitPhrase)

    // tests the program printed output (as dictated
    // by keyboard input)
    testSame(
        captureResults(
            { impReactConsole(doneYetState) },
            "howdy",
            "cool",
            "getting old",
            exitPhrase,
        ),
        CapturedResult(
            Unit,
            "$startPhrase!",
            "howdy!",
            "cool!",
            "getting old!",
            "$exitPhrase!!!",
        ),
        "done yet?: I/O",
    )
}

runEnabledTests(this)
