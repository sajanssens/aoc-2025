package nl.bramjanssens

fun main() {

    val invalidIds1 = mutableListOf<Long>()
    val invalidIds2 = mutableListOf<Long>()

    for (line in lines(2, InputType.Main)) {
        if (line.isBlank()) continue

        for (range in line.split(",")) {
            if (range.isBlank()) continue
            val split = range.split("-")
            val from = split[0].toLong()
            val to = split[1].toLong()

            for (idLong in from..to) {
                val id = idLong.toString()

                // PART 1
                val firstHalf = id.take(id.length / 2)
                val secondHalf = id.substring(id.length / 2)
                if (firstHalf == secondHalf)
                    invalidIds1.add(id.toLong())

                // PART 2
                if ("^(\\d+)(\\1)+$".toRegex().matches(id))
                    invalidIds2.add(id.toLong())
            }
        }
    }
    println(invalidIds1.sum())
    println(invalidIds2.sum())
}