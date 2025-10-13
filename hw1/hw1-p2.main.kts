// -----------------------------------------------------------------
// Homework 1, Problem 2
// -----------------------------------------------------------------

// For any word of at least one character that starts with a
// letter, let's say that its "bingo word" is the uppercase
// version of the first letter, followed by a space, and then
// followed by the number of characters in the word. For example,
// the bingo word of "bingo" is "B 5" and the bingo word of "Win"
// is "W 3".

// TODO 1/1: Write the function bingoWord that takes a string as
//           an argument and returns its bingo word. You may
//           assume that the argument is a valid word as described
//           above. Now try it out a couple times by outputting a
//           word and its corresponding result in your main.
//
//           Reminder: given a string s, s.uppercase() converts it
//                     to caps and s.first() gets the first letter.
//

fun bingoWord(s: String): String {
    // outputs the first character (capitalized) and the length of the string sperated by a space
    return "${s.first().uppercase()} ${s.length}"
}

fun main() {
    // testing bingoWord function
    val word1 = "bingo"
    val expectedResult1 = "B 5"
    println("tried: $word1, got ${bingoWord(word1)}, expected: $expectedResult1")

    val word2 = "Win"
    val expectedResult2 = "W 3"
    println("tried: $word2, got ${bingoWord(word2)}, expected: $expectedResult2")
}

main()
