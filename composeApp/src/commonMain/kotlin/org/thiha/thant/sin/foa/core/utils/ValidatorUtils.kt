package org.thiha.thant.sin.foa.core.utils

import org.thiha.thant.sin.foa.core.EMAIL_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.ENTER_CONFIRM_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.ENTER_EMAIL_TEXT
import org.thiha.thant.sin.foa.core.ENTER_NAME_TEXT
import org.thiha.thant.sin.foa.core.ENTER_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.NAME_TOO_SHORT_TEXT
import org.thiha.thant.sin.foa.core.NAME_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.PASSWORD_MIN_LENGTH_TEXT
import org.thiha.thant.sin.foa.core.PASSWORD_NOT_MATCH_TEXT

object ValidatorUtils {


    fun checkNameValidation(name: String): String {
        if (name.isBlank()) {
            return ENTER_NAME_TEXT
        }

        val namePattern = "^[A-Za-z\\s.'-]{2,}$".toRegex()
        if (!name.matches(namePattern)) {
            return NAME_VALID_FORMAT_TEXT
        }

        if (name.length < 2) {
            return NAME_TOO_SHORT_TEXT
        }

        return ""
    }


    fun checkEmailValidation(email: String): String {
        if (email.isBlank()) {
            return ENTER_EMAIL_TEXT
        }

        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!email.matches(emailPattern)) {
            return EMAIL_VALID_FORMAT_TEXT
        }

        return ""
    }


    fun checkPasswordValidation(password: String): String {
        if (password.isBlank()) {
            return ENTER_PASSWORD_TEXT
        }

        if (password.length < 6) {
            return PASSWORD_MIN_LENGTH_TEXT
        }

        return ""
    }

    fun checkConfirmPasswordValidation(password: String, confirmPassword: String): String {
        if (confirmPassword.isBlank()) {
            return ENTER_CONFIRM_PASSWORD_TEXT
        }

        if (confirmPassword != password) {
            return PASSWORD_NOT_MATCH_TEXT
        }

        return ""
    }


}