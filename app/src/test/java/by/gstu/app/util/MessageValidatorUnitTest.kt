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
class MessageValidatorUnitTest {
    private lateinit var validator: MessageValidator

    @BeforeEach
    fun setUp() {
        validator = MessageValidator()
    }

    @AfterEach
    fun tearDown() {}

    @ParameterizedTest(name = "#{index} - Run test with message = {0}")
    @MethodSource("validMessages")
    fun testThatInvalidMessageIsPassedValidator(message: String?) {
        assertTrue(validator.isValid(message))
    }

    @ParameterizedTest(name = "#{index} - Run test with message = {0}")
    @MethodSource("invalidMessages")
    fun testThatInvalidMessageIsNotPassedValidator(message: String?) {
        assertFalse(validator.isValid(message))
    }

    companion object {
        @JvmStatic
        fun validMessages() : Stream<String> {
            return Stream.of("Hello it's me", "ls call me back later", "hello",
                "12345", "______", "#####", "!!!!!", "$$$$$",)
        }
        @JvmStatic
        fun invalidMessages() : Stream<String> {
            return Stream.of("abc", "\n\r\t",
                "\n", "\t\t", "1", "#$%@", ".",
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111",
                " ", "", "               ", "\t\t\t\t\t\t\t", null)
        }
    }
}