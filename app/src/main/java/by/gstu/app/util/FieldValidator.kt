package by.gstu.app.util

abstract class FieldValidator<T> {
    var resourceValueMessage: Int = 0

    abstract fun isValid(data: T) : Boolean
}