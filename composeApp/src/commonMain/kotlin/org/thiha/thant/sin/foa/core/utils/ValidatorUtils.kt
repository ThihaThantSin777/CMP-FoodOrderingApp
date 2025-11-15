package org.thiha.thant.sin.foa.core.utils

import org.thiha.thant.sin.foa.core.CARD_NUMBER_INVALID_TEXT
import org.thiha.thant.sin.foa.core.CARD_NUMBER_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.CVV_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.EMAIL_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.ENTER_ADDRESS_TEXT
import org.thiha.thant.sin.foa.core.ENTER_CARD_NUMBER_TEXT
import org.thiha.thant.sin.foa.core.ENTER_CONFIRM_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.ENTER_CVV_TEXT
import org.thiha.thant.sin.foa.core.ENTER_EMAIL_TEXT
import org.thiha.thant.sin.foa.core.ENTER_EXPIRY_TEXT
import org.thiha.thant.sin.foa.core.ENTER_NAME_ON_CARD_TEXT
import org.thiha.thant.sin.foa.core.ENTER_NAME_TEXT
import org.thiha.thant.sin.foa.core.ENTER_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.EXPIRY_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.NAME_ON_CARD_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.NAME_TOO_SHORT_TEXT
import org.thiha.thant.sin.foa.core.NAME_VALID_FORMAT_TEXT
import org.thiha.thant.sin.foa.core.PASSWORD_MIN_LENGTH_TEXT
import org.thiha.thant.sin.foa.core.PASSWORD_NOT_MATCH_TEXT
import kotlin.time.Clock

object ValidatorUtils {

    fun checkNameValidation(name: String): String {
        if (name.isBlank()) return ENTER_NAME_TEXT

        val namePattern = "^[A-Za-z\\s.'-]{2,}$".toRegex()
        if (!name.matches(namePattern)) return NAME_VALID_FORMAT_TEXT

        if (name.length < 2) return NAME_TOO_SHORT_TEXT

        return ""
    }

    fun checkEmailValidation(email: String): String {
        if (email.isBlank()) return ENTER_EMAIL_TEXT

        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!email.matches(emailPattern)) return EMAIL_VALID_FORMAT_TEXT

        return ""
    }

    fun checkPasswordValidation(password: String): String {
        if (password.isBlank()) return ENTER_PASSWORD_TEXT
        if (password.length < 6) return PASSWORD_MIN_LENGTH_TEXT
        return ""
    }

    fun checkConfirmPasswordValidation(password: String, confirmPassword: String): String {
        if (confirmPassword.isBlank()) return ENTER_CONFIRM_PASSWORD_TEXT
        if (confirmPassword != password) return PASSWORD_NOT_MATCH_TEXT
        return ""
    }


    fun checkCardNumberValidation(cardNumber: String): String {
        val raw = cardNumber.replace(" ", "").replace("-", "")
        if (raw.isBlank()) return ENTER_CARD_NUMBER_TEXT
        if (!raw.matches(Regex("^\\d{12,19}$"))) return CARD_NUMBER_VALID_FORMAT_TEXT
        return ""
    }

    fun checkExpiryValidation(expiry: String): String {
        if (expiry.isBlank()) return ENTER_EXPIRY_TEXT

        val isValidFormat = Regex("^(0[1-9]|1[0-2])/(\\d{2})$").matches(expiry)
        if (!isValidFormat) return EXPIRY_VALID_FORMAT_TEXT

        return ""
    }

    fun checkCvvValidation(cvv: String): String {
        if (cvv.isBlank()) return ENTER_CVV_TEXT
        if (!cvv.matches(Regex("^\\d{3,4}$"))) return CVV_VALID_FORMAT_TEXT
        return ""
    }

    fun checkNameOnCardValidation(name: String): String {
        if (name.isBlank()) return ENTER_NAME_ON_CARD_TEXT
        val pattern = "^[A-Za-z\\s.'-]{2,}$".toRegex()
        if (!name.matches(pattern)) return NAME_ON_CARD_VALID_FORMAT_TEXT
        return ""
    }

    fun checkAddressValidation(address: String): String {
        if (address.isBlank()) return ENTER_ADDRESS_TEXT
        return ""
    }

}
