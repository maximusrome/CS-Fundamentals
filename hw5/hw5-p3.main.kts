// -----------------------------------------------------------------
// Homework 5, Problem 3
// -----------------------------------------------------------------

import khoury.EnabledTest
import khoury.runEnabledTests
import khoury.testSame

// In this problem you'll practice designing a data class that has
// methods, making your first upgrade to the project.

// TODO 1/1: Design the data type TaggedFlashCard to represent a
//           single flash card.
//
//           You should be able to represent the text prompt on
//           the front of the card, the text answer on the back,
//           as well as any number of textual tags (such as "hard"
//           or "science" -- this shouldn't come from any fixed
//           set of options, but truly open to however someone
//           wishes to categorize their cards).
//
//           Each card should have two convenience methods:
//           - isTagged, which determines if the card has a
//             supplied tag (e.g., has this card been tagged
//             as "hard"?)
//           - fileFormat, which produces a textual representation
//             of the card as "front|back|tag1,tag2,..."; that is
//             all three parts of the card separated with the pipe
//             ('|') character, and further separate any tags with
//             a comma (',')
//
//           Include *at least* 3 example cards, and make sure to
//           test the methods (in a single testTaggedFlashCard
//           function that has been annotated as @EnabledTest).
//

val sepCard = "|"
val sepTag = ","
// (just useful values for
// the separation characters)

data class TaggedFlashCard(val front: String, val back: String, val tags: List<String>) {
    // Determines if the card has a supplied tag.
    fun isTagged(tag: String): Boolean = tags.contains(tag)

    // Produces a textual representation of the card.
    fun fileFormat(): String = "$front$sepCard$back$sepCard${tags.joinToString(sepTag)}"
}

// Example cards
val card1 = TaggedFlashCard("Who's Batman's sidekick?", "Robin", listOf("comics"))
val card2 = TaggedFlashCard("H2O is?", "Water", listOf("science"))
val card3 = TaggedFlashCard("Capital of Japan?", "Tokyo", listOf("geo"))

@EnabledTest
fun testTaggedFlashCard() {
    testSame(
        card1.isTagged("comics"),
        true,
        "card1 is tagged as comics",
    )
    testSame(
        card2.isTagged("science"),
        true,
        "card2 is tagged as science",
    )
    testSame(
        card3.isTagged("geo"),
        true,
        "card3 is tagged as geo",
    )
    testSame(
        card1.isTagged("science"),
        false,
        "card1 is not tagged as science",
    )
    testSame(
        card2.isTagged("geo"),
        false,
        "card2 is not tagged as geo",
    )
    testSame(
        card3.isTagged("comics"),
        false,
        "card3 is not tagged as comics",
    )
    testSame(
        card1.fileFormat(),
        "Who's Batman's sidekick?|Robin|comics",
        "card1 file format",
    )
    testSame(
        card2.fileFormat(),
        "H2O is?|Water|science",
        "card2 file format",
    )
    testSame(
        card3.fileFormat(),
        "Capital of Japan?|Tokyo|geo",
        "card3 file format",
    )
}

runEnabledTests(this)
