package nl.bramjanssens

import nl.bramjanssens.InputType.Main
import kotlin.math.abs

fun main() {
    var password = 0
    var dial = 50
    for (rot in lines(1, Main)) {
        var inc = rot.substring(1).toInt()
        if (rot[0] == 'L') inc = 100 - inc
        dial = (dial + inc) % 100
        if (dial == 0) password++
    }
    println(password)

    var dial2 = BoundedNumber(100, 50)
    for (rot in lines(1, Main)) {
        val dir = rot[0]
        val inc = rot.substring(1).toInt()
        if (dir == 'R') dial2 += inc
        if (dir == 'L') dial2 -= inc
    }
    println(dial2.timesPointedAtZero)
}

class BoundedNumber(val toExcl: Int, var value: Int) {
    var timesPointedAtZero: Int = 0

    operator fun plus(i: Int): BoundedNumber {
        val newValue = this.value + i
        this.timesPointedAtZero += newValue / toExcl
        this.value = newValue % toExcl
        return this
    }

    operator fun minus(i: Int): BoundedNumber {
        val newValue = this.value - i
        if (this.value > 0 && newValue <= 0) {
            this.timesPointedAtZero += (abs(newValue) / toExcl) + 1
        }
        if (this.value == 0) {
            this.timesPointedAtZero += abs(newValue) / toExcl
        }
        this.value = Math.floorMod(newValue, toExcl)
        return this
    }
}