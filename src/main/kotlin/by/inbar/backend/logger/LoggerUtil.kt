package by.inbar.backend.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Provide method for making log.
 */
inline fun <reified T> T.logger(): Logger =
    LoggerFactory.getLogger(if (T::class.isCompanion) T::class.java.enclosingClass else T::class.java)
