package nl.bramjanssens

import nl.bramjanssens.InputType.Main

fun main() {
    var password = 0
    var dial = 50
    for (rot in lines(1, Main)) {
        var inc = rot.substring(1).toInt()
        if (rot.get(0) == 'L') inc = 100 - inc
        dial = (dial + inc) % 100
        if (dial == 0) password++
    }
    println(password)
}
