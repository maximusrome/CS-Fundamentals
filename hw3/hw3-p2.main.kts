// -----------------------------------------------------------------
// Homework 3, Problem 2
// -----------------------------------------------------------------

// TODO 1/4: Design the data type FlashCard to represent a single
//           flash card. You should be able to represent the text
//           prompt on the front of the card as well as the text
//           answer on the back. Include at least 3 example cards
//           (which will come in handy later for tests!).
//

// TODO 2/4: Design the data type Deck to represent a deck of
//           flash cards. The deck should have a name, as well
//           as a sequence of flash cards.
//
//           Include at least 2 example decks based upon the
//           card examples above.
//

// TODO 3/4: Design the predicate areAllOneWordAnswers that
//           determines if the backs of all the cards in a deck
//           are a single word (i.e., have no spaces, which
//           includes a card with a blank back).
//
//           Hint: hidden in the name of this function is a
//                 reminder of a useful list function to use :)
//

// A couple potentially helpful examples for tests
// val fcEmptyBack = FlashCard("Front", "")
// val fcLongBack = FlashCard("Front", "Long answer")

// TODO 4/4: Design the predicate anyContainsPhrase that determines
//           if any of the cards in a deck contain the supplied
//           phrase.
//
//           Hints:
//           - string1.contains(string2) will be quite useful
//             here :)
//           - Again, the name of this function hints at a useful
//             list function we learned!
//

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// TODO 1/4: Design the data type FlashCard
// Data class representing a flash card with front and back text.
data class FlashCard(val front: String, val back: String)

// TODO 2/4: Design the data type Deck
// Data class representing a deck of flash cards with a name.
data class Deck(val name: String, val cards: List<FlashCard>)

// TODO 3/4: Design the predicate areAllOneWordAnswers
// Checks if all answers in a deck are single-word answers.
fun areAllOneWordAnswers(deck: Deck): Boolean {
    return deck.cards.all { !it.back.contains(" ") }
}

// TODO 4/4: Design the predicate anyContainsPhrase
// Checks if any card in a deck contains the given phrase.
fun anyContainsPhrase(
    deck: Deck,
    phrase: String,
): Boolean {
    return deck.cards.any { it.front.contains(phrase) || it.back.contains(phrase) }
}

// Example cards
val cardA = FlashCard("Who wrote 'Romeo and Juliet'?", "Shakespeare")
val cardB = FlashCard("What's H2O?", "Water")
val cardC = FlashCard("Pi is approximately?", "3.14")

// Example decks
val litDeck = Deck("Lit Basics", listOf(cardA))
val chemDeck = Deck("Chem Basics", listOf(cardB))
val mathDeck = Deck("Math Facts", listOf(cardC))

@EnabledTest
fun testAreAllOneWordAnswers() {
    val testDeck = Deck("Mixed", listOf(cardA, cardB, cardC))

    testSame(
        areAllOneWordAnswers(litDeck),
        true,
        "Lit Deck Test",
    )

    testSame(
        areAllOneWordAnswers(chemDeck),
        true,
        "Chem Deck Test",
    )

    testSame(
        areAllOneWordAnswers(mathDeck),
        true,
        "Math Deck Test",
    )
}

@EnabledTest
fun testAnyContainsPhrase() {
    testSame(
        anyContainsPhrase(litDeck, "Romeo"),
        true,
        "Romeo in Lit Deck",
    )

    testSame(
        anyContainsPhrase(chemDeck, "H2"),
        true,
        "H2 in Chem Deck",
    )

    testSame(
        anyContainsPhrase(mathDeck, "3.15"),
        false,
        "3.15 in Math Deck",
    )
}

runEnabledTests(this)
