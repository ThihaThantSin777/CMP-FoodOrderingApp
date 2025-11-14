package org.thiha.thant.sin.foa.auth.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO
import org.thiha.thant.sin.foa.auth.network.api_service.AuthApiService
import org.thiha.thant.sin.foa.auth.network.api_service.impl.AuthApiServiceImpl

object AuthRepository {
    val apiService: AuthApiService = AuthApiServiceImpl;

    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.login(loginRequestVO);
            return@withContext response;
        }
    }

    suspend fun register(registerRequestVO: RegisterRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.register(registerRequestVO);
            return@withContext response;
        }
    }
}