package by.gstu.app.util

import by.gstu.app.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class AbonentNameValidator : FieldValidator<String?>() {
    companion object {
        private const val MIN_RANGE = 3
        private const val MAX_RANGE = 18
        val PATTERN: Pattern = Pattern.compile(
                String.format("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){%d,%d}[a-zA-Z0-9]\$",
                        MIN_RANGE, MAX_RANGE))
    }

    override fun isValid(data: String?): Boolean {

        if (NameValidators().isFieldNullOrEmpty(data)) {
            resourceValueMessage = R.string.empty_contact_name_field_error
            return false
        }
        val preparedString: String = data.toString()
        if (NameValidators().isFieldTooShort(preparedString) ||
                NameValidators().isFieldTooLong(preparedString)) {
            resourceValueMessage = R.string.incorrect_contact_name_field_error
            return false
        }
        if (!NameValidators().doesFieldMatchTemplate(preparedString)) {
            resourceValueMessage = R.string.pattern_contact_name_error
            return false
        }
        return true
    }

    private inner class NameValidators {

        fun isFieldNullOrEmpty(field: String?): Boolean {
            return field == null || field.isEmpty()
        }

        fun isFieldTooShort(field: String): Boolean {
            return field.length < MIN_RANGE
        }

        fun isFieldTooLong(field: String): Boolean {
            return field.length > MAX_RANGE
        }

        fun doesFieldMatchTemplate(field: String): Boolean {
            val matcher: Matcher = PATTERN.matcher(field)
            return matcher.matches()
        }
    }
}