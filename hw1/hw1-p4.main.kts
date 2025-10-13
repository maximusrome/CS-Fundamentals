// -----------------------------------------------------------------
// Homework 1, Problem 4
// -----------------------------------------------------------------

// Let's try to make a useful app to help schedule your time :)
//
// First, we'll define a couple constants about the world...

val DAYS_PER_WEEK = "7"
val HOURS_PER_DAY = "24"

// Ok, your spidy sense should be tingling...
// why are these strings!?
//
// Good job :)
// Honestly we just want you to have some practice converting
// types of data, but this *totally* comes up when you ask someone
// to type a value, or read it from a file of data.
//
// As a reminder, given a string s (that we assume contains a
// textual representation of an integer), s.toInt() gets you that
// integer.
//
// Alright, we are now going to use this to "model" a student's
// free time in the week, assuming they *must* do nothing but
// sleep and school work.
//
// TODO 1/1: Write the function freeTime that accepts integers,
//           all assumed to be reasonable, representing...
//            - the number of hours per night for good sleep,
//            - the number of classes being taken;
//            - and number of hours per class to succeed (which
//              includes in-class, office hours, studying, etc).
//
//            and then returns the number of remaining hours in
//            each week for eating/exploring/fun-ing :)
//
//            For example, a student that needs 8 hours rest
//            per night, taking 4 classes (which each need 10
//            hours of work), would have...
//
//             (7 * 24) - (7 * 8) - (4 * 10) = 72
//
//            hours each week for all sorts of other activities :)
//
//            Once written, try out the function in your main with
//            this example and one other.
//

fun freeTime(
    hoursOfSleep: Int,
    numberOfClasses: Int,
    hoursPerClass: Int,
): Int {
    // outputs total free time based on totalHoursInAWeek, totalSleepHours, and totalClassHours
    val totalHoursInAWeek = DAYS_PER_WEEK.toInt() * HOURS_PER_DAY.toInt()
    val totalSleepHours = hoursOfSleep * DAYS_PER_WEEK.toInt()
    val totalClassHours = numberOfClasses * hoursPerClass
    return totalHoursInAWeek - (totalSleepHours + totalClassHours)
}

fun main() {
    // testing the freeTime function
    println(freeTime(8, 4, 10)) // Expected Result: 72
    println(freeTime(7, 5, 9)) // Expected Result: 74
}

main()
