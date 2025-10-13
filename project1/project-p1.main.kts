import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.fileExists
import khoury.fileReadAsList
import khoury.isAnInteger
import khoury.linesToString
import khoury.reactConsole
import khoury.runEnabledTests
import khoury.testSame
// -----------------------------------------------------------------
// Project: Part 1, Summary
// -----------------------------------------------------------------

// You are going to design an application to allow a user to
// self-study using flash cards. In this part of the project,
// a user will...

// 1. Be prompted to choose from a menu of available flash
//    card decks; this menu will repeat until a valid
//    selection is made.
//
// 2. Proceed through each card in the selected deck,
//    one-by-one. For each card, the front is displayed,
//    and the user is allowed time to reflect; then the
//    back is displayed; and the user is asked if they
//    got the correct answer.
//
// 3. Once the deck is exhausted, the program outputs the
//    number of self-reported correct answers and ends.
//

// Of course, we'll design this program step-by-step, AND
// you've already done pieces of this in homework!!
// (Note: you are welcome to leverage your prior work and/or
// code found in the sample solutions & lecture notes.)
//

// Lastly, here are a few overall project requirements...
// - Since mutation hasn't been covered in class, your design is
//   NOT allowed to make use of mutable variables and/or lists.
// - As included in the instructions, all interactive parts of
//   this program MUST make effective use of the reactConsole
//   framework.
// - Staying consistent with our Style Guide...
//   * All functions must have:
//     a) a preceding comment specifying what it does
//     b) an associated @EnabledTest function with sufficient
//        tests using testSame
//   * All data must have:
//     a) a preceding comment specifying what it represents
//     b) associated representative examples
// - You will be evaluated on a number of criteria, including...
//   * Adherence to instructions and the Style Guide
//   * Correctly producing the functionality of the program
//   * Design decisions that include choice of tests, appropriate
//     application of list abstractions, and task/type-driven
//     decomposition of functions.
//

// -----------------------------------------------------------------
// Data design
// (Hint: see Homework 3, Problem 2)
// -----------------------------------------------------------------

// TODO 1/2: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).
//

// Represents a flash card with text
// on the front (prompt) and back (answer).
data class FlashCard(val front: String, val back: String)

// Example questions, answers, and FlashCards.
val qMA = "What is the capital of Massachusetts, USA?"
val aMA = "Boston"

val qCA = "What is the capital of California, USA?"
val aCA = "Sacramento"

val qUK = "What is the capital of the United Kingdom?"
val aUK = "London"

val fcMA = FlashCard(qMA, aMA)
val fcCA = FlashCard(qCA, aCA)
val fcUK = FlashCard(qUK, aUK)

// TODO 2/2: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a Kotlin list of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.
//

val nameUS = "US Capitals"
val nameWorld = "World Capitals"

// Represents a deck with a name and list of cards.
data class Deck(val name: String, val cards: List<FlashCard>)

// Example decks based on cards.
val deckUS = Deck(nameUS, listOf(fcMA, fcCA))
val deckWorld = Deck(nameWorld, listOf(fcMA, fcCA, fcUK))

// -----------------------------------------------------------------
// Generating flash cards
// -----------------------------------------------------------------

// One benefit of digital flash cards is that sometimes we can
// use code to produce cards that match a known pattern without
// having to write all the fronts/backs by hand!
//

// TODO 1/1: Design the function perfectSquares that takes a
//           count (assumed to be positive) and produces the
//           list of flash cards that tests that number of the
//           first squares.
//
//           For example, the first three perfect squares...
//
//            1. front (1^2 = ?), back (1)
//            2. front (2^2 = ?), back (4)
//            3. front (3^2 = ?), back (9)
//
//           have been supplied as named values.
//
//           Hint: you might consider combining your
//                 kthPerfectSquare function from Homework 1
//                 with the list constructor in Homework 3.
//

val square1Front = "1^2 = ?"
val square2Front = "2^2 = ?"
val square3Front = "3^2 = ?"

val square1Back = "1"
val square2Back = "4"
val square3Back = "9"

// Helper function that generates a FlashCard for the square of (index + 1).
fun makeFlashCard(index: Int): FlashCard {
    val value = index + 1
    val square = value * value
    return FlashCard("$value^2 = ?", "$square")
}

@EnabledTest
fun testMakeFlashCard() {
    testSame(
        makeFlashCard(0),
        FlashCard(square1Front, square1Back),
        "FlashCard for index 0",
    )
    testSame(
        makeFlashCard(1),
        FlashCard(square2Front, square2Back),
        "FlashCard for index 1",
    )
    testSame(
        makeFlashCard(2),
        FlashCard(square3Front, square3Back),
        "FlashCard for index 2",
    )
}

// Creates a list of flashcards representing perfect squares.
fun perfectSquares(count: Int): List<FlashCard> {
    return List(count, ::makeFlashCard)
}

@EnabledTest
fun testPerfectSquares() {
    testSame(
        perfectSquares(0),
        emptyList<FlashCard>(),
        "0 count",
    )
    testSame(
        perfectSquares(1),
        listOf(
            FlashCard(square1Front, square1Back),
        ),
        "First perfect square",
    )
    testSame(
        perfectSquares(3),
        listOf(
            FlashCard(square1Front, square1Back),
            FlashCard(square2Front, square2Back),
            FlashCard(square3Front, square3Back),
        ),
        "First 3 perfect squares",
    )
}

// -----------------------------------------------------------------
// Files of cards
// -----------------------------------------------------------------

// Consider a simple format for storing flash cards in a file:
// each card is a line in the file, where the front comes first,
// separated by a "pipe" character ('|'), followed by the text
// on the back of the card.
//

val charSep = "|"

// TODO 1/3: Design the function cardToString that takes a flash
//           card as input and produces a string according to the
//           specification above ("front|back"). Make sure to
//           test all your card examples!
//

// Transforms a FlashCard into a formatted String.
fun cardToString(card: FlashCard): String {
    return "${card.front}$charSep${card.back}"
}

@EnabledTest
fun testCardToString() {
    testSame(
        cardToString(FlashCard("", "")),
        "|",
        "Empty card",
    )
    testSame(
        cardToString(fcMA),
        "What is the capital of Massachusetts, USA?|Boston",
        "MA capital",
    )
    testSame(
        cardToString(fcCA),
        "What is the capital of California, USA?|Sacramento",
        "CA capital",
    )
    testSame(
        cardToString(fcUK),
        "What is the capital of the United Kingdom?|London",
        "UK capital",
    )
}

// TODO 2/3: Design the function stringToCard that takes a string,
//           assumed to be in the format described above, and
//           produces the corresponding flash card.
//
//           Hints:
//           - look back to how we extracted data from CSV
//             (comma-separated value) files (such as in
//             Homework 3)!
//           - a great way to test: for each of your card
//             examples, pass them through the function in TODO
//             1 to convert them to a string; then, pass that
//             result to this function... you *should* get your
//             original flash card back :)
//

// Transforms a given formatted String back into a FlashCard.
fun stringToCard(cardString: String): FlashCard {
    val parts = cardString.split(charSep)
    return FlashCard(parts[0], parts[1])
}

@EnabledTest
fun testStringToCard() {
    testSame(
        stringToCard("|"),
        FlashCard("", ""),
        "Empty string",
    )
    testSame(
        stringToCard(cardToString(fcMA)),
        fcMA,
        "MA capital",
    )
    testSame(
        stringToCard(cardToString(fcCA)),
        fcCA,
        "CA capital",
    )
    testSame(
        stringToCard(cardToString(fcUK)),
        fcUK,
        "UK capital",
    )
}

// TODO 3/3: Design the function readCardsFile that takes a path
//           to a file and produces the corresponding list of
//           flash cards found in the file.
//
//           If the file does not exist, return an empty list.
//           Otherwise, you can assume that every line is
//           formatted in the string format we just worked with.
//
//           Hint:
//           - Think about how HW3-P1 effectively used an
//             abstraction to process all the lines in a
//             file assuming a known pattern.
//           - We've provided an "example.txt" file that you can
//             use for testing if you'd like; also make sure to
//             test your function when the supplied file does not
//             exist!
//

// Reads a file by its path and retrieves a list of flash cards.
// If the file does not exist, an empty list is returned.
fun readCardsFile(filePath: String): List<FlashCard> {
    if (fileExists(filePath)) {
        return fileReadAsList(filePath).map(::stringToCard)
    }
    return emptyList()
}

@EnabledTest
fun testReadCardsFile() {
    testSame(
        readCardsFile("nonexistent.txt"),
        emptyList<FlashCard>(),
        "Nonexistent file",
    )
    testSame(
        readCardsFile("example.txt"),
        listOf(
            FlashCard("What is the color of the ocean?", "Blue"),
            FlashCard("What is the color of leaves?", "Green"),
        ),
        "Valid txt file",
    )
}

// -----------------------------------------------------------------
// Processing a self-report
// (Hint: see Homework 2)
// -----------------------------------------------------------------

// In our program, we will ask for a self-report as to whether
// the user got the correct answer for a card, SO...

// TODO 1/1: Finish designing the function isPositive that
//           determines if the supplied string starts with
//           the letter "y" (either upper or lowercase).
//
//           You've been supplied with a number of tests - make
//           sure you understand what they are doing!
//

// Verifies if the provided string starts with either "y" or "Y".
fun isPositive(input: String): Boolean {
    return input[0].lowercase() == "y"
}

@EnabledTest
fun testIsPositive() {
    fun helpTest(
        str: String,
        expected: Boolean,
    ) {
        testSame(isPositive(str), expected, str)
    }

    helpTest("yes", true)
    helpTest("Yes", true)
    helpTest("YES", true)
    helpTest("yup", true)

    helpTest("nope", false)
    helpTest("NO", false)
    helpTest("nah", false)
    helpTest("not a chance", false)

    // should pass,
    // despite doing the wrong thing
    helpTest("indeed", false)
}

// -----------------------------------------------------------------
// Choosing a deck from a menu
// -----------------------------------------------------------------

// Now let's work on providing a menu of decks from which a user
// can choose what they want to study.

// TODO 1/2: Finish design the function choicesToText that takes
//           a list of strings (assumed to be non-empty) and
//           produces the textual representation of a menu of
//           those options.
//
//           For example, given...
//
//           ["a", "b", "c"]
//
//           The menu would be...
//
//           "1. a
//            2. b
//            3. c
//
//            Enter your choice"
//
//            As you have probably guessed, this will be a key
//            piece of our rendering function :)
//
//            Hints:
//            - Think back to Homework 3 when we used a list
//              constructor to generate list elements based
//              upon an index.
//            - If you can produce a list of strings, the
//              linesToString function in the Khoury library
//              will bring them together into a single string.
//            - Make sure to understand the supplied tests!
//

val promptMenu = "Enter your choice"

// Constructs a formatted menu string from a set of deck names,
// asking the user to pick an option.
fun choicesToText(deckNames: List<String>): String {
    fun formatDeckOption(name: String): String {
        return "${deckNames.indexOf(name) + 1}. $name"
    }
    val formattedOptions = deckNames.map(::formatDeckOption)
    return "${linesToString(formattedOptions)}\n\n$promptMenu"
}

@EnabledTest
fun testChoicesToText() {
    val optA = "apple"
    val optB = "banana"
    val optC = "carrot"

    testSame(
        choicesToText(listOf(optA)),
        linesToString(
            "1. $optA",
            "",
            promptMenu,
        ),
        "one",
    )

    testSame(
        choicesToText(listOf(optA, optB, optC)),
        linesToString(
            "1. $optA",
            "2. $optB",
            "3. $optC",
            "",
            promptMenu,
        ),
        "three",
    )
}

// TODO 2/2: Finish designing the program chooseOption that takes
//           a list of decks, produces a corresponding numbered
//           menu (1-# of decks, each showing its name), and
//           returns the deck corresponding to the number entered.
//           (Of course, keep displaying the menu until a valid
//           number is entered.)
//
//           Hints:
//            - Review the "Valid Number Example" of reactConsole
//              as one example of how to validate input. In this
//              case, however, since we know that we have a valid
//              range of integers, we can simplify the state
//              representation significantly :)
//            - To help you get started, the chooseOption function
//              has been written, but you must complete the helper
//              functions; look to the comments below for guidance.
//              You can then play "signature detective" to figure
//              out the parameters/return type of the functions you
//              need to write :)
//            - Lastly, as always, don't forget to sufficiently
//              test all the functions you write in this problem!
//

// Fetches the name of the provided deck.
fun getDeckName(deck: Deck): String = deck.name

@EnabledTest
fun testGetDeckName() {
    testSame(
        getDeckName(deckUS),
        "US Capitals",
        "US capitals deck",
    )
    testSame(
        getDeckName(deckWorld),
        "World Capitals",
        "World capitals deck",
    )
}

// Confirms if the entered input lies within the acceptable
// range of integers for deck choice.
fun keepIfValid(
    input: String,
    validIndices: IntRange,
): Int {
    if (isAnInteger(input)) {
        val intValue = input.toInt()
        if (intValue in 1..(validIndices.last + 1)) {
            return intValue - 1
        }
    }
    return -1
}

@EnabledTest
fun testKeepIfValid() {
    testSame(
        keepIfValid("", 0..2),
        -1,
        "Empty input",
    )
    testSame(
        keepIfValid("0", 0..2),
        -1,
        "Zero input",
    )
    testSame(
        keepIfValid("1", 0..2),
        0,
        "Valid input 1",
    )
    testSame(
        keepIfValid("2", 0..2),
        1,
        "Valid input 2",
    )
    testSame(
        keepIfValid("3", 0..2),
        2,
        "Valid input 3",
    )
    testSame(
        keepIfValid("4", 0..2),
        -1,
        "Invalid input 4",
    )
    testSame(
        keepIfValid("-1", 0..2),
        -1,
        "Negative input",
    )
    testSame(
        keepIfValid("a", 0..2),
        -1,
        "Non-integer input a",
    )
}

// Produces an announcement string indicating the selected deck name.
fun choiceAnnouncement(chosenDeckName: String): String {
    return "You chose: $chosenDeckName"
}

@EnabledTest
fun testChoiceAnnouncement() {
    testSame(
        choiceAnnouncement(nameUS),
        "You chose: US Capitals",
        "Choosing US capitals deck",
    )
    testSame(
        choiceAnnouncement(nameWorld),
        "You chose: World Capitals",
        "Choosing world capitals deck",
    )
    testSame(
        choiceAnnouncement(""),
        "You chose: ",
        "Empty announcement",
    )
}

// a program to allow the user to interactively select
// a deck from the supplied, non-empty list of decks
fun chooseOption(decks: List<Deck>): Deck {
    // since the event handlers will need some info about
    // the supplied decks, the functions inside
    // chooseOption provide info about them while the
    // parameter is in scope

    // TODO: Above chooseOption, design the function
    //       getDeckName, which returns the name of
    //       a supplied deck.
    fun renderDeckOptions(state: Int): String {
        return choicesToText(decks.map(::getDeckName))
    }

    // TODO: Above chooseOption, design the function
    //       keepIfValid, that takes the typed input
    //       as a string, as well as the valid
    //       indices of the decks; note that the list indices
    //       will be in the range [0, size), whereas the
    //       user will see and work with [1, size].
    //
    //       If the user did not type a valid integer,
    //       or not one in [1, size], return -1; otherwise
    //       return the string converted to an integer, but
    //       subtract 1, which makes it a valid list index.
    fun transitionOptionChoice(
        ignoredState: Int,
        kbInput: String,
    ): Int {
        return keepIfValid(kbInput, decks.indices)
    }

    // TODO: nothing, but understand this :)
    fun validChoiceEntered(state: Int): Boolean {
        return state in decks.indices
    }

    // TODO: Above chooseOption, design the function
    //       choiceAnnouncement that takes the selected
    //       deck name and returns an announcement that
    //       makes you happy. For a simple example, given
    //       "fundies" as the chosen deck name, you might
    //       return "you chose: fundies"
    fun renderChoice(state: Int): String {
        return choiceAnnouncement(getDeckName(decks[state]))
    }

    return decks[
        reactConsole(
            initialState = -1,
            stateToText = ::renderDeckOptions,
            nextState = ::transitionOptionChoice,
            isTerminalState = ::validChoiceEntered,
            terminalStateToText = ::renderChoice,
        ),
    ]
}

@EnabledTest
fun testChooseOption() {
    // Helper function to wrap chooseOption for testing
    fun helpTest(decks: List<Deck>): () -> Deck {
        fun chooseMyOption(): Deck {
            return chooseOption(decks)
        }
        return ::chooseMyOption
    }

    testSame(
        captureResults(
            helpTest(listOf(deckUS, deckWorld)),
            "1",
        ),
        CapturedResult(
            deckUS,
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "You chose: US Capitals",
        ),
        "Direct valid input",
    )

    testSame(
        captureResults(
            helpTest(listOf(deckUS, deckWorld)),
            "-2",
            "12",
            "2",
        ),
        CapturedResult(
            deckWorld,
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "You chose: World Capitals",
        ),
        "Invalid and out-of-range choices",
    )

    testSame(
        captureResults(
            helpTest(listOf(deckUS, deckWorld)),
            "non-integer",
            "2",
        ),
        CapturedResult(
            deckWorld,
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "1. US Capitals",
            "2. World Capitals",
            "",
            "Enter your choice",
            "You chose: World Capitals",
        ),
        "Test non-integer input",
    )
}

// -----------------------------------------------------------------
// Studying a deck
// -----------------------------------------------------------------

// Now let's design a program to allow a user to study through a
// supplied deck of flash cards.

// TODO 1/2: Design the data type StudyState to keep track of...
//           - which card you are currently studying in the deck
//           - are you looking at the front or back
//           - how many correct answers have been self-reported
//             thus far
//
//           Create sufficient examples so that you convince
//           yourself that you can represent any situation that
//           might arise when studying a deck.
//
//           Hints:
//           - Look back to the reactConsole problems in HW2 and
//             HW3; the former involved keeping track of a count
//             of loops (similar to the count of correct answers),
//             and the latter involved options for keeping track
//             of where you are in a list with reactConsole.
//

// Represents the present state of a user's
// study session with a deck of flash cards.
data class StudyState(val currentIndex: Int, val isViewingBack: Boolean, val correctAnswers: Int)

// Examples for StudyState
val firstCardFront = StudyState(0, false, 0)
val firstCardBack = StudyState(0, true, 0)
val secondCardFront = StudyState(1, false, 0)
val secondCardBack = StudyState(1, true, 0)
val thirdCardFront = StudyState(2, false, 0)
val thirdCardBack = StudyState(2, true, 0)
val studyCompleted = StudyState(3, true, 1)

// TODO 2/2: Now, using reactConsole, design the program studyDeck
//           that for each card in a supplied deck, allows the
//           user to...
//
//           1. see the front (pause and think)
//           2. see the back
//           3. respond as to whether they got the answer
//
//           At the end, the user is told how many they self-
//           reported as correct (and this number is returned).
//
//           You have been supplied some prompts for steps #1
//           and #2 - feel free to change them if you'd like :)
//
//           Suggestions...
//           - Review the reactConsole videos/examples
//           - Start with studyDeck:
//             * write some tests to convince yourself you know
//               what your program is supposed to do!
//             * figure out how you'll create the initial state
//             * give names to the handlers you'll need
//             * how will you return the number correct?
//             * now comment-out this function, so that you can
//               design/test the handlers without interference :)
//           - For each handler...
//             * Play signature detective: based upon how it's
//               being used with reactConsole, what data will it
//               be given and what does it produce?
//             * Write some tests to convince yourself you know
//               its job.
//             * Write the code and don't move on till your tests
//               pass.
//            - Suggested ordering...
//              1. Am I done studying yet?
//              2. Rendering
//                 - It's a bit simpler to have a separate
//                   function for the terminal state.
//                 - The linesToString function is your friend to
//                   combine the card with the prompts.
//                 - Think about good decomposition when making
//                   the decision about front vs back content.
//              3. Transition
//                 - Start with the two main situations
//                   you'll find yourself in...
//                   > front->back
//                   > back->front
//                 - Then let a helper figure out how to handle
//                   the details of self-report
//
//            You've got this :-)
//

val studyThink = "Think of the result? Press enter to continue"
val studyCheck = "Correct? (Y)es/(N)o"

// Determines if the study session has concluded.
fun isDoneStudying(
    state: StudyState,
    deck: Deck,
): Boolean {
    return state.currentIndex >= deck.cards.size
}

@EnabledTest
fun testIsDoneStudying() {
    testSame(
        isDoneStudying(firstCardFront, Deck("Empty Deck", listOf())),
        true,
        "Empty deck",
    )
    testSame(
        isDoneStudying(firstCardFront, deckWorld),
        false,
        "Studying first card (front)",
    )

    testSame(
        isDoneStudying(firstCardBack, deckWorld),
        false,
        "Studying first card (back)",
    )

    testSame(
        isDoneStudying(secondCardFront, deckWorld),
        false,
        "Studying second card (front)",
    )

    testSame(
        isDoneStudying(secondCardBack, deckWorld),
        false,
        "Studying second card (back)",
    )

    testSame(
        isDoneStudying(thirdCardFront, deckWorld),
        false,
        "Studying third card (front)",
    )

    testSame(
        isDoneStudying(thirdCardBack, deckWorld),
        false,
        "Studying third card (back)",
    )

    testSame(
        isDoneStudying(studyCompleted, deckWorld),
        true,
        "Study completed",
    )
}

// Helper function to get the content of the
// active card based on the study state.
fun getCurrentCardContent(
    state: StudyState,
    deck: Deck,
): String {
    val currentCard = deck.cards[state.currentIndex]
    if (!state.isViewingBack) {
        return currentCard.front
    } else {
        return currentCard.back
    }
}

@EnabledTest
fun testGetCurrentCardContent() {
    testSame(
        getCurrentCardContent(firstCardFront, deckWorld),
        "What is the capital of Massachusetts, USA?",
        "Content of first card (front)",
    )
    testSame(
        getCurrentCardContent(firstCardBack, deckWorld),
        "Boston",
        "Content of first card (back)",
    )
    testSame(
        getCurrentCardContent(secondCardFront, deckWorld),
        "What is the capital of California, USA?",
        "Content of second card (front)",
    )
    testSame(
        getCurrentCardContent(secondCardBack, deckWorld),
        "Sacramento",
        "Content of second card (back)",
    )
    testSame(
        getCurrentCardContent(thirdCardFront, deckWorld),
        "What is the capital of the United Kingdom?",
        "Content of third card (front)",
    )
    testSame(
        getCurrentCardContent(thirdCardBack, deckWorld),
        "London",
        "Content of third card (back)",
    )
}

// Helper function that fetches the prompt
// for the user according to the study state.
fun getCurrentPrompt(state: StudyState): String {
    if (!state.isViewingBack) {
        return studyThink
    } else {
        return studyCheck
    }
}

@EnabledTest
fun testGetCurrentPrompt() {
    testSame(
        getCurrentPrompt(StudyState(0, false, 0)),
        "Think of the result? Press enter to continue",
        "Prompt for front of card",
    )
    testSame(
        getCurrentPrompt(StudyState(0, true, 0)),
        "Correct? (Y)es/(N)o",
        "Prompt for back of card",
    )
}

// Presents the front or back of the card with
// relevant prompts to the user.
fun renderCard(
    state: StudyState,
    deck: Deck,
): String {
    if (isDoneStudying(state, deck)) {
        return "Done studying"
    }
    val cardContent = getCurrentCardContent(state, deck)
    val prompt = getCurrentPrompt(state)
    return "$cardContent\n$prompt"
}

@EnabledTest
fun testRenderCard() {
    testSame(
        renderCard(firstCardFront, deckWorld),
        "What is the capital of Massachusetts, USA?\n" +
            "Think of the result? Press enter to continue",
        "Render first card (front) from deckWorld",
    )
    testSame(
        renderCard(firstCardBack, deckWorld),
        "Boston\nCorrect? (Y)es/(N)o",
        "Render first card (back) from deckWorld",
    )
    testSame(
        renderCard(secondCardFront, deckWorld),
        "What is the capital of California, USA?\n" +
            "Think of the result? Press enter to continue",
        "Render second card (front) from deckWorld",
    )
    testSame(
        renderCard(secondCardBack, deckWorld),
        "Sacramento\nCorrect? (Y)es/(N)o",
        "Render second card (back) from deckWorld",
    )
    testSame(
        renderCard(thirdCardFront, deckWorld),
        "What is the capital of the United Kingdom?\n" +
            "Think of the result? Press enter to continue",
        "Render third card (front) from deckWorld",
    )
    testSame(
        renderCard(thirdCardBack, deckWorld),
        "London\nCorrect? (Y)es/(N)o",
        "Render third card (back) from deckWorld",
    )
    testSame(
        renderCard(studyCompleted, deckWorld),
        "Done studying",
        "Render after finishing the study session",
    )
    testSame(
        renderCard(firstCardFront, Deck("Empty Deck", listOf())),
        "Done studying",
        "Render first card (front) from an empty deck",
    )
}

// Helper function facilitating the move from a card's front to its back.
fun transitionToFront(state: StudyState): StudyState {
    return StudyState(state.currentIndex, true, state.correctAnswers)
}

@EnabledTest
fun testTransitionToFront() {
    testSame(
        transitionToFront(firstCardFront),
        StudyState(0, true, 0),
        "Transition first card from front to back",
    )
    testSame(
        transitionToFront(secondCardFront),
        StudyState(1, true, 0),
        "Transition second card from front to back",
    )
    testSame(
        transitionToFront(thirdCardFront),
        StudyState(2, true, 0),
        "Transition third card from front to back",
    )
    testSame(
        transitionToFront(firstCardBack),
        StudyState(0, true, 0),
        "Transition first card that's already on its back",
    )
    testSame(
        transitionToFront(studyCompleted),
        StudyState(3, true, 1),
        "Transition beyond deck's length",
    )
}

// Helper function to move to the subsequent card,
// incrementing the correct answers if the user's response was positive.
fun transitionToNextCard(
    state: StudyState,
    input: String,
): StudyState {
    val isAnswerCorrect = isPositive(input)
    val correctAnswerCount = if (isAnswerCorrect) state.correctAnswers + 1 else state.correctAnswers
    return StudyState(state.currentIndex + 1, false, correctAnswerCount)
}

@EnabledTest
fun testTransitionToNextCard() {
    testSame(
        transitionToNextCard(firstCardBack, "yes"),
        StudyState(1, false, 1),
        "Transition with positive response",
    )
    testSame(
        transitionToNextCard(firstCardBack, "no"),
        StudyState(1, false, 0),
        "Transition with negative response",
    )
    testSame(
        transitionToNextCard(thirdCardBack, "yes"),
        StudyState(3, false, 1),
        "Transition from the last card",
    )
}

// Determines the next state based on the current
// study state, user input, and deck.
fun transitionCard(
    state: StudyState,
    input: String,
    deck: Deck,
): StudyState {
    if (isDoneStudying(state, deck)) {
        return state
    }
    return when {
        !state.isViewingBack -> transitionToFront(state)
        else -> transitionToNextCard(state, input)
    }
}

@EnabledTest
fun testTransitionCard() {
    testSame(
        transitionCard(firstCardFront, "", deckWorld),
        StudyState(0, true, 0),
        "Transitioning from front to back of a card",
    )
    testSame(
        transitionCard(firstCardBack, "yes", deckWorld),
        StudyState(1, false, 1),
        "Transitioning to next card with a positive response",
    )
    testSame(
        transitionCard(firstCardBack, "no", deckWorld),
        StudyState(1, false, 0),
        "Transitioning to next card with a negative response",
    )
    testSame(
        transitionCard(studyCompleted, "y", deckWorld),
        studyCompleted,
        "Transitioning when already done studying",
    )
}

// Begins a flashcard study phase, guiding the user across each card,
// and returns the total of self-reported accurate answers.
fun studyDeck(deck: Deck): Int {
    return reactConsole(
        initialState = firstCardFront,
        stateToText = { state: StudyState -> renderCard(state, deck) },
        nextState = { state: StudyState, input: String -> transitionCard(state, input, deck) },
        isTerminalState = { state: StudyState -> isDoneStudying(state, deck) },
        terminalStateToText = { state: StudyState -> "You got ${state.correctAnswers} correct answers" },
    ).correctAnswers
}

@EnabledTest
fun testStudyDeck() {
    // Helper function to wrap studyDeck for testing
    fun helpTest(deck: Deck): () -> Int {
        fun studyMyDeck(): Int {
            return studyDeck(deck)
        }
        return ::studyMyDeck
    }

    testSame(
        captureResults(
            helpTest(Deck("Empty Deck", emptyList<FlashCard>())),
        ),
        CapturedResult(
            0,
            "You got 0 correct answers",
        ),
        "Empty deck",
    )

    testSame(
        captureResults(
            helpTest(deckUS),
            "",
            "y",
            "",
            "yes",
        ),
        CapturedResult(
            2,
            "What is the capital of Massachusetts, USA?",
            "Think of the result? Press enter to continue",
            "Boston",
            "Correct? (Y)es/(N)o",
            "What is the capital of California, USA?",
            "Think of the result? Press enter to continue",
            "Sacramento",
            "Correct? (Y)es/(N)o",
            "You got 2 correct answers",
        ),
        "Test deckUS",
    )

    testSame(
        captureResults(
            helpTest(deckWorld),
            "Boston",
            "YES",
            "Sacramento",
            "nope",
            "London",
            "yup",
        ),
        CapturedResult(
            2,
            "What is the capital of Massachusetts, USA?",
            "Think of the result? Press enter to continue",
            "Boston",
            "Correct? (Y)es/(N)o",
            "What is the capital of California, USA?",
            "Think of the result? Press enter to continue",
            "Sacramento",
            "Correct? (Y)es/(N)o",
            "What is the capital of the United Kingdom?",
            "Think of the result? Press enter to continue",
            "London",
            "Correct? (Y)es/(N)o",
            "You got 2 correct answers",
        ),
        "Test deckWorld",
    )

    testSame(
        captureResults(
            helpTest(deckWorld),
            "",
            "NO",
            "wrong input",
            "no",
            "nah",
            "n",
            "not a chance",
            "indeed",
        ),
        CapturedResult(
            0,
            "What is the capital of Massachusetts, USA?",
            "Think of the result? Press enter to continue",
            "Boston",
            "Correct? (Y)es/(N)o",
            "What is the capital of California, USA?",
            "Think of the result? Press enter to continue",
            "Sacramento",
            "Correct? (Y)es/(N)o",
            "What is the capital of the United Kingdom?",
            "Think of the result? Press enter to continue",
            "London",
            "Correct? (Y)es/(N)o",
            "You got 0 correct answers",
        ),
        "test invalid answers",
    )
}

// -----------------------------------------------------------------
// Final app!
// -----------------------------------------------------------------

// Now you just get to put this all together 💃

// TODO 1/1: Design the function chooseAndStudy, where you'll
//           follow the comments in the supplied code to leverage
//           your prior work to allow the user to choose a deck,
//           study it, and return the number of correct self-
//           reports.
//
//           Your deck options MUST include at least one from each
//           of the following categories...
//
//           - Coded by hand (such as an example in data design)
//           - Read from a file (ala readCardsFile)
//           - Generated by code (ala perfectSquares)
//
//           Note: while this is an interactive program, you won't
//                 directly use reactConsole - instead, just call
//                 the programs you already designed above :)
//
//           And of course, don't forget to test at least two runs
//           of this completed program!
//
//           (And, consider adding this to main so you can see the
//           results of all your hard work so far this semester!)
//

// lets the user choose a deck and study it,
// returning the number self-reported correct
fun chooseAndStudy(): Int {
    // 1. Construct a list of options
    // (ala the instructions above)
    val deckOptions =
        listOf(
            // TODO: at least...
            // deck from file via readCardsFile,
            // deck from code via perfectSquares
            // deck hand-coded
            Deck("Deck From File", readCardsFile("example.txt")),
            Deck("Perfect Squares", perfectSquares(2)),
            deckUS,
        )

    // 2. Use chooseOption to let the user
    //    select a deck
    val deckChosen = chooseOption(deckOptions)

    // 3. Let the user study, return the
    //    number correctly answered
    return studyDeck(deckChosen)
}

@EnabledTest
fun testChooseAndStudy() {
    // Helper function to wrap chooseAndStudy for testing
    fun helpTest(): () -> Int {
        fun chooseAndStudyNest(): Int {
            return chooseAndStudy()
        }
        return ::chooseAndStudy
    }

    testSame(
        captureResults(
            helpTest(),
            "5",
            "a",
            "3",
            "",
            "y",
            "",
            "n",
        ),
        CapturedResult(
            1,
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "You chose: US Capitals",
            "What is the capital of Massachusetts, USA?",
            "Think of the result? Press enter to continue",
            "Boston",
            "Correct? (Y)es/(N)o",
            "What is the capital of California, USA?",
            "Think of the result? Press enter to continue",
            "Sacramento",
            "Correct? (Y)es/(N)o",
            "You got 1 correct answers",
        ),
        "ChooseAndStudy US capitals",
    )

    testSame(
        captureResults(
            helpTest(),
            "-1",
            "2",
            "",
            "yes",
            "",
            "NO",
        ),
        CapturedResult(
            1,
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "You chose: Perfect Squares",
            "1^2 = ?",
            "Think of the result? Press enter to continue",
            "1",
            "Correct? (Y)es/(N)o",
            "2^2 = ?",
            "Think of the result? Press enter to continue",
            "4",
            "Correct? (Y)es/(N)o",
            "You got 1 correct answers",
        ),
        "ChooseAndStudy perfect squares",
    )

    testSame(
        captureResults(
            helpTest(),
            "1",
            "",
            "y",
            "",
            "n",
        ),
        CapturedResult(
            1,
            "1. Deck From File",
            "2. Perfect Squares",
            "3. US Capitals",
            "",
            "Enter your choice",
            "You chose: Deck From File",
            "What is the color of the ocean?",
            "Think of the result? Press enter to continue",
            "Blue",
            "Correct? (Y)es/(N)o",
            "What is the color of leaves?",
            "Think of the result? Press enter to continue",
            "Green",
            "Correct? (Y)es/(N)o",
            "You got 1 correct answers",
        ),
        "ChooseAndStudy deck from file",
    )
}

// -----------------------------------------------------------------

fun main() {
    chooseAndStudy()
}

runEnabledTests(this)
main()
