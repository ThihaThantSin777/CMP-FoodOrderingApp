package org.thiha.thant.sin.foa.auth.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordRequestVO(
    @SerialName("name")
    val email: String,

    @SerialName("password")
    val password: String?
) {
}