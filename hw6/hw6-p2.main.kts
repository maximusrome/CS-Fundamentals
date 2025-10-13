// -----------------------------------------------------------------
// Homework 6, Problem 2
// -----------------------------------------------------------------

import khoury.CapturedResult
import khoury.EnabledTest
import khoury.captureResults
import khoury.runEnabledTests
import khoury.testSame

// In this problem, you will practice with looping and mutation to
// decode an (admittedly simple) encrypted message :)

// TODO 1/1: Consider the following data design...

// represents the information needed to decode
// information in a list of strings:
// - phraseIndex is which string
// - characterIndex is which character in
//   that string
// - nextNodeIndex is which sneaky node
//   to move to next (or -1 to end)
data class SneakyNode(
    val phraseIndex: Int,
    val characterIndex: Int,
    val nextNodeIndex: Int,
)

//           Finish designing secretMessage that accepts a list of
//           these sneaky nodes, as well as a corresponding list of
//           phrases, and prints to the screen the message that is
//           jointly encoded.
//
//           The function starts at the first sneaky node in the
//           supplied list, outputs the character in the phrase
//           identified via this node's indexes, and keeps going
//           until the next node's index is -1.
//
//           You have been supplied tests that should pass once the
//           function has been completed - make sure you understand
//           them (in part as a way to understand this encoding
//           approach!).
//

// Decodes an encrypted message by traversing SneakyNodes,
// each pointing to a character in a list of phrases.
fun secretMessage(
    nodes: List<SneakyNode>,
    phrases: List<String>,
) {
    var currentNodeIndex = 0
    while (currentNodeIndex != -1) {
        val node = nodes[currentNodeIndex]
        print(phrases[node.phraseIndex][node.characterIndex])
        currentNodeIndex = node.nextNodeIndex
    }
    println()
}

@EnabledTest
fun testSecretMessage() {
    val phrases =
        listOf(
            "how are you doing?",
            "i hope you have a lovely day!",
            "programming is fun :)",
        )

    val startNode1 = SneakyNode(2, 19, 8)
    val startNode2 = SneakyNode(1, 2, 2)

    val otherNodes =
        listOf(
            SneakyNode(0, 2, 5),
            SneakyNode(2, 2, 1),
            SneakyNode(1, 28, -1),
            SneakyNode(2, 0, -1),
            SneakyNode(0, 12, 6),
            SneakyNode(1, 27, 3),
            SneakyNode(1, 1, 1),
            SneakyNode(2, 20, -1),
        )

    testSame(
        captureResults(
            {
                secretMessage(
                    listOf(startNode1) + otherNodes,
                    phrases,
                )
            },
        ),
        CapturedResult(
            Unit,
            ":)",
        ),
        "example 1",
    )

    testSame(
        captureResults(
            {
                secretMessage(
                    listOf(startNode2) + otherNodes,
                    phrases,
                )
            },
        ),
        CapturedResult(
            Unit,
            "howdy!",
        ),
        "example 2",
    )
}

runEnabledTests(this)
