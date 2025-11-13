package org.thiha.thant.sin.foa.auth.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.network.api_service.ApiService
import org.thiha.thant.sin.foa.auth.network.api_service.impl.ApiServiceImpl

object LoginRepository {
    val apiService: ApiService = ApiServiceImpl;

    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.login(loginRequestVO);
            return@withContext response;
        }
    }
}