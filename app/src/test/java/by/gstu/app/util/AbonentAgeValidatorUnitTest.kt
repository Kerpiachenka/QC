package by.gstu.app.util

import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AbonentAgeValidatorUnitTest {

    private lateinit var validator: AbonentAgeValidator

    @BeforeEach
    fun setUp() {
        validator = AbonentAgeValidator()
    }

    @AfterEach
    fun tearDown() {}

    @ParameterizedTest(name = "#{index} - Run test with age = {0}")
    @MethodSource("validAgeValues")
    fun testThatAgeValidValuesAreValid(age: Int?) {
        assertTrue(validator.isValid(age))
    }

    @ParameterizedTest(name = "#{index} - Run test with age = {0}")
    @MethodSource("invalidAgeValues")
    fun testThatAgeInvalidValuesAreNotValid(age: Int?) {
        assertFalse(validator.isValid(age))
    }

    companion object {
        @JvmStatic
        fun validAgeValues() : Stream<Int> {
            return Stream.of(10, 20, 25, 31, 37, 40, 66, 77, 88, 90)
        }
        @JvmStatic
        fun invalidAgeValues() : Stream<Int> {
            return Stream.of(Int.MIN_VALUE, Int.MAX_VALUE, 0, -1, -10, -11, 91, 99,
                101, null, -0, +0, 9)
        }
    }
}