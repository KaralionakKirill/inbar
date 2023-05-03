package by.inbar.backend.exception

open class InbarException(
    override val message: String,

    val httpCode: Int
): Exception()
