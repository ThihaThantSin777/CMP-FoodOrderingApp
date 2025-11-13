package org.thiha.thant.sin.foa.auth.network.api_service

import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO

interface ApiService {
    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO
}