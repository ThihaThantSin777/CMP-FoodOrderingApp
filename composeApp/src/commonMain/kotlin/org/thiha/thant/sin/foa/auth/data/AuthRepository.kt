package org.thiha.thant.sin.foa.auth.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.State
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordVO
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO
import org.thiha.thant.sin.foa.auth.network.api_service.AuthApiService
import org.thiha.thant.sin.foa.auth.network.api_service.impl.AuthApiServiceImpl

object AuthRepository {
    val apiService: AuthApiService = AuthApiServiceImpl;

    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.login(loginRequestVO);
            ///TODO Need to Delect After implement Persistent
            State.token = response.accessToken
            return@withContext response;
        }
    }

    suspend fun register(registerRequestVO: RegisterRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.register(registerRequestVO);
            ///TODO Need to Delect After implement Persistent
            State.token = response.accessToken
            return@withContext response;
        }
    }

    suspend fun forgetPasswordCheck(forgetPasswordRequestVO: ForgetPasswordRequestVO): ForgetPasswordVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.forgetPasswordCheck(forgetPasswordRequestVO);
            return@withContext response;
        }
    }

    suspend fun forgetPassword(
        forgetPasswordRequestVO: ForgetPasswordRequestVO,
        resetPasswordToken: String
    ) {
        return withContext(Dispatchers.IO) {
            val response = apiService.forgetPassword(forgetPasswordRequestVO, resetPasswordToken);
            return@withContext response;
        }
    }
}