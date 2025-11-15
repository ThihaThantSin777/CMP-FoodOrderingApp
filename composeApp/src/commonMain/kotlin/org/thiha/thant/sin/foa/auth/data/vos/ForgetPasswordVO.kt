package org.thiha.thant.sin.foa.auth.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordVO(
    @SerialName("user") val user: User,

    @SerialName("reset_password_token") val resetPasswordToken: String
)

@Serializable
data class User(
    @SerialName("id") val id: Long,

    @SerialName("email") val email: String,

    @SerialName("fullname") val fullName: String,

    @SerialName("created_at") val createdAt: String,

    @SerialName("updated_at") val updatedAt: String
)