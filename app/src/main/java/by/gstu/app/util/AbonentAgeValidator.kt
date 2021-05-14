package by.gstu.app.util

import by.gstu.app.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class AbonentAgeValidator : FieldValidator<Int?>() {
    companion object {
        const val MIN_AGE = 10
        const val MAX_AGE = 90
    }

    override fun isValid(data: Int?): Boolean {
        if (AgeValidator().isAgeNull(data)) {
            resourceValueMessage = R.string.empty_age_field_error
            return false
        }
        val preparedAge : Int = data!!
        if (AgeValidator().isAgeLessThanMinRange(preparedAge) ||
                AgeValidator().isAgeOutOfMaxRange(preparedAge)) {
            resourceValueMessage = R.string.incorrect_age_field_error
            return false
        }
        return true
    }

    private inner class AgeValidator {
        fun isAgeNull(data: Int?) : Boolean {
            return data == null
        }
        fun isAgeLessThanMinRange(data: Int) : Boolean {
            return data < MIN_AGE
        }
        fun isAgeOutOfMaxRange(data: Int) : Boolean {
            return data > MAX_AGE
        }
    }
}