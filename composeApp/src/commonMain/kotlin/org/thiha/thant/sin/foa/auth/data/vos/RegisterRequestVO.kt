package org.thiha.thant.sin.foa.auth.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestVO(
    @SerialName("email")
    val email: String,
    @SerialName("fullname")
    val fullName: String,
    @SerialName("password")
    val password: String
) {
}