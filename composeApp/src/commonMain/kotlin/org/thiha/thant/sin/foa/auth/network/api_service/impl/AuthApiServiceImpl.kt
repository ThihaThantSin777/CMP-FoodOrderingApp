package org.thiha.thant.sin.foa.auth.network.api_service.impl

import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordVO
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO
import org.thiha.thant.sin.foa.auth.network.api_service.AuthApiService
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_FORGET_PASSWORD
import org.thiha.thant.sin.foa.core.utils.API_FORGET_PASSWORD_CHECK_EMAIL
import org.thiha.thant.sin.foa.core.utils.API_LOGIN
import org.thiha.thant.sin.foa.core.utils.API_REGISTER

object AuthApiServiceImpl : AuthApiService {

    override suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        val httpResponse = HttpClientProvider.httpClient.post(API_LOGIN) {
            setBody(loginRequestVO)
        }

        return transformResult<AuthVO>(httpResponse)

    }

    override suspend fun register(registerRequestVO: RegisterRequestVO): AuthVO {
        val httpResponse = HttpClientProvider.httpClient.post(API_REGISTER) {
            setBody(registerRequestVO)
        }

        return transformResult<AuthVO>(httpResponse)

    }

    override suspend fun forgetPasswordCheck(forgetPasswordRequestVO: ForgetPasswordRequestVO): ForgetPasswordVO {
        val httpResponse = HttpClientProvider.httpClient.post(API_FORGET_PASSWORD_CHECK_EMAIL) {
            setBody(forgetPasswordRequestVO)
        }

        return transformResult<ForgetPasswordVO>(httpResponse)
    }

    override suspend fun forgetPassword(
        forgetPasswordRequestVO: ForgetPasswordRequestVO,
        resetPasswordToken: String
    ) {
        val httpResponse = HttpClientProvider.httpClient.post(API_FORGET_PASSWORD) {
            header(HttpHeaders.Authorization, "Bearer $resetPasswordToken")
            setBody(forgetPasswordRequestVO)
        }

        return transformResult<Unit>(httpResponse)
    }
}