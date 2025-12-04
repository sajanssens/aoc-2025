package nl.bramjanssens

fun main() {
    // This is a classic greedy algorithm for forming the largest number by selecting a fixed number of digits in order.

    val jolts = mutableListOf<Long>()
    for (line in lines(3, InputType.Test)) {
        if (line.isBlank()) continue
        val digits = mutableListOf<Int>()
        var from = 0
        val batteryCount = 12
        // Iterate through the digit sequence 12 times, to find 12 most significant digits
        for (i in 0..<batteryCount) {
            // for each step, create a window starting just after the previous pickedDigit
            // initial width = line.length-batteryCount+1 (i.e. 4), but it shrinks when we jump more than 1 until it's only 1 element wide
            val windowRange = from..(line.length - batteryCount) + i
            val window = windowRange.map { DigitAt(line[it].digitToInt(), it) }
            val maxDigitInWindow = window.maxBy { it.value }
            digits += maxDigitInWindow.value
            from = maxDigitInWindow.index + 1 // for the next step, jump forward to the index just after the picked element
        }
        val jolt = digits.joinToString("").toLong()
        jolts.add(jolt)
    }
    println(jolts.sum())
}

data class DigitAt(val value: Int, val index: Int)