// -----------------------------------------------------------------
// Homework 1, Problem 3
// -----------------------------------------------------------------

// TODO 1/1: Write the function makeGreeter that takes a string
//           and produces a function, which then outputs a
//           greeting to any supplied name. Ok, that was a bit
//           confusing, so here's an example...
//
//           val myGreeter = makeGreeter("Howdy")
//           println(myGreeter("World"))
//
//           which should output "Howdy World" to the screen.
//
//           Once you think you have it, try using it for a couple
//           greetings and names in your main, similar to the
//           example above.
//
//           Hint: remember that "referencing" a function as a
//                 value is slightly different from calling it;
//                 the latter is just functionName(argument),
//                 whereas the former involves using the ::'s,
//                 such as ::functionName.
//

fun makeGreeter(greeting: String): (String) -> String {
    // outputs a function which outputs a greeting to a name
    fun greeterFunction(name: String): String {
        return "$greeting $name"
    }
    return ::greeterFunction
}

fun main() {
    // testing makeGreeter function
    val myGreeter = makeGreeter("Howdy")
    println(myGreeter("World"))

    val anotherGreeter = makeGreeter("Hello")
    println(anotherGreeter("Alice"))
}

main()
