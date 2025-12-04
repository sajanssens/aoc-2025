package nl.bramjanssens

fun main() {

    val lines = lines(4, InputType.Main)
    val height = lines.size
    val width = lines[0].length

    var charGridOriginal: Array<Array<Char>> = Array(height) { Array(width) { ' ' } }
    var charGridNew: Array<Array<Char>> = Array(height) { Array(width) { ' ' } }
    for ((row, line) in lines.withIndex()) {
        for ((col, ch) in line.withIndex()) {
            charGridOriginal[row][col] = ch
        }
    }

    var sum = 0
    while (true) { // comment out for part 1
        var accessible = 0

        for (row in 0 until height) {
            for (col in 0 until width) {
                val ch = charGridOriginal[row][col]
                charGridNew[row][col] = ch
                if (ch == '@') {
                    val neighbors = findNeighbors(row, col, charGridOriginal)
                    val atCount = neighbors.count { it == '@' }
                    if (atCount < 4) {
                        accessible++
                        charGridNew[row][col] = '.'
                    }
                }
            }
        }
        charGridOriginal = charGridNew.map { it.copyOf() }.toTypedArray()
        charGridNew = Array(height) { Array(width) { ' ' } }

        sum += accessible
        if (accessible == 0) break // comment out for part 1
    } // comment out for part 1
    println(sum)
}

private fun findNeighbors(row: Int, col: Int, charGrid: Array<Array<Char>>): MutableList<Char> {
    val neighbors = mutableListOf<Char>()
    val height = charGrid.size
    val width = if (height > 0) charGrid[0].size else 0
    for (dr in -1..1) {
        for (dc in -1..1) {
            if (dr == 0 && dc == 0) continue
            val nr = row + dr
            val nc = col + dc
            if (nr in 0 until height && nc in 0 until width) {
                neighbors.add(charGrid[nr][nc])
            }
        }
    }
    return neighbors
}

