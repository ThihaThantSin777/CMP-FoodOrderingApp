package org.thiha.thant.sin.foa.auth.data.vos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthVO(
    @SerialName("id")
    val id: Int,

    @SerialName("email")
    val email: String,

    @SerialName("fullname")
    val fullName: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("access_token")
    val accessToken: String
) {

}