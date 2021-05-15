package by.gstu.app.util

import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AbonentNameValidatorUnitTest {

    private lateinit var validator: AbonentNameValidator

    @BeforeEach
    fun setUp() {
        validator = AbonentNameValidator()
    }

    @AfterEach
    fun tearDown() {}

    @ParameterizedTest(name = "#{index} - Run test with name = {0}")
    @MethodSource("validNames")
    fun testThatInvalidUsernameIsPassedValidator(contactName: String?) {
        assertTrue(validator.isValid(contactName))
    }

    @ParameterizedTest(name = "#{index} - Run test with name = {0}")
    @MethodSource("invalidNames")
    fun testThatInvalidUsernameIsNotPassedValidator(contactName: String?) {
        assertFalse(validator.isValid(contactName))
    }

    companion object {
        @JvmStatic
        fun validNames() : Stream<String> {
            return Stream.of("mkyong", "nikola-tsl", "nikola.tsl",
                "java.regex", "java-regex", "java_regex", "java.regex.123", "java-regex-123",
                "java_regex_123", "javaregex123", "123456", "java123", "01234567890123456")
        }
        @JvmStatic
        fun invalidNames() : Stream<String> {
            return Stream.of("abc", "01234567890123456789a",
                "_javaregex_", ".javaregex.", "-javaregex-", "javaregex#$%@123", "java..regex",
                "java--regex", "java__regex", "java._regex", "java.-regex", " ", "")
        }
    }
}