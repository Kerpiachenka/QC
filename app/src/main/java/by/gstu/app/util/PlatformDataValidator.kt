package by.gstu.app.util

import by.gstu.app.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class PlatformDataValidator : FieldValidator<String?>() {
    companion object {
        private const val MIN_RANGE = 3
        private const val MAX_RANGE = 18
        val PATTERN: Pattern = Pattern.compile(
                String.format("([A-Za-z0-9]+=[A-Za-z0-9:;*_,.]+;)+",
                        MIN_RANGE, MAX_RANGE))
    }

    override fun isValid(data: String?): Boolean {
        if (DataValidators().isFieldNullOrEmpty(data)) {
            resourceValueMessage = R.string.empty_data_field_error
            return false
        }
        val preparedString: String = data.toString()
        if (!DataValidators().doesFieldMatchTemplate(preparedString)) {
            resourceValueMessage = R.string.pattern_platform_data_error
            return false
        }
        return true
    }

    private inner class DataValidators {

        fun isFieldNullOrEmpty(field: String?): Boolean {
            return field == null || field.isEmpty()
        }

        fun doesFieldMatchTemplate(field: String): Boolean {
            val matcher: Matcher = PATTERN.matcher(field)
            return matcher.matches()
        }
    }
}