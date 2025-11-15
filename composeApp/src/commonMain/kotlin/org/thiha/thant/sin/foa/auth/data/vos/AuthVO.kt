package org.thiha.thant.sin.foa.auth.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class AuthVO(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,

    @SerialName("email")
    @ColumnInfo("email")
    val email: String,

    @SerialName("fullname")
    @ColumnInfo("full_name")
    val fullName: String,

    @SerialName("created_at")
    @ColumnInfo("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    @ColumnInfo("updated_at")
    val updatedAt: String,

    @SerialName("access_token")
    @ColumnInfo("access_token")
    val accessToken: String
)