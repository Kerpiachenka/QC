package by.gstu.app.util

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
class PlatformDataValidatorUnitTest {
    private lateinit var validator: PlatformDataValidator

    @BeforeEach
    fun setUp() {
        validator = PlatformDataValidator()
    }

    @AfterEach
    fun tearDown() {}

    @ParameterizedTest(name = "#{index} - Run test with data = {0}")
    @MethodSource("validData")
    fun testThatValidDataIsPassedValidator(data: String?) {
        assertTrue(validator.isValid(data))
    }

    @ParameterizedTest(name = "#{index} - Run test with data = {0}")
    @MethodSource("invalidData")
    fun testThatInvalidDataIsNotPassedValidator(data: String?) {
        assertFalse(validator.isValid(data))
    }

    companion object {
        @JvmStatic
        fun validData() : Stream<String> {
            return Stream.of("key=Value;", "k=v1;key=VALUE223;",
                "v=k1;key1=value1;key2=value2;", "kkk=vvv;")
        }
        @JvmStatic
        fun invalidData() : Stream<String> {
            return Stream.of("abc", "\n\r\t", "key = value;", "key\n=val1;key=\tval2;",
                "\n", "\t\t", "1", "#$%@", ".", "key=value", "k==1;k===3", ";1=k",
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111",
                " ", "", "               ", "\t\t\t\t\t\t\t", null)
        }
    }
}