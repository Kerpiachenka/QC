package by.gstu.app.util

import by.gstu.app.R

class MessageValidator : FieldValidator<String?>() {
    companion object {
        private const val MIN_RANGE = 5
        private const val MAX_RANGE = 50
    }

    override fun isValid(data: String?): Boolean {

        if (MessageValidators().isFieldNullOrEmpty(data)) {
            resourceValueMessage = R.string.empty_contact_name_field_error
            return false
        }
        val preparedString: String = data.toString()
        if (MessageValidators().isFieldTooShort(preparedString) ||
                MessageValidators().isFieldTooLong(preparedString)) {
            resourceValueMessage = R.string.incorrect_message_size_error
            return false
        }
        return true
    }

    private inner class MessageValidators {

        fun isFieldNullOrEmpty(field: String?): Boolean {
            return field == null || field.isEmpty()
        }

        fun isFieldTooShort(field: String): Boolean {
            return field.length < MIN_RANGE
        }

        fun isFieldTooLong(field: String): Boolean {
            return field.length > MAX_RANGE
        }
    }
}