package nl.bramjanssens

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoundedNumberTest : WithAssertions {
    @ParameterizedTest
    @CsvSource(
        "0, 110, 10, 1",
        "0, 80, 80, 0",
        "10, 80, 90, 0",
        "10, 110, 20, 1",
        "10, 550, 60, 5",
        "10, 90, 0, 1",
    )
    fun plus(v: Int, i: Int, expectedNewValue: Int, expectedOverflowCount: Int) {
        var b = BoundedNumber(100, v)
        b += i
        assertThat(b.value).isEqualTo(expectedNewValue)
        assertThat(b.timesPointedAtZero).isEqualTo(expectedOverflowCount)
    }

    @ParameterizedTest
    @CsvSource(
        "50, 40, 10, 0",
        "50, 50, 0, 1",
        "50, 60, 90, 1",
        "50, 160, 90, 2",
        "0, 1, 99, 0",
        "0, 120, 80, 1",
    )
    fun minus(v: Int, i: Int, expectedNewValue: Int, expectedOverflowCount: Int) {
        var b = BoundedNumber(100, v)
        b -= i
        assertThat(b.value).isEqualTo(expectedNewValue)
        assertThat(b.timesPointedAtZero).isEqualTo(expectedOverflowCount)
    }

}