package org.thiha.thant.sin.foa.auth.network.api_service

import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO

interface AuthApiService {
    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO

    suspend fun register(registerRequestVO: RegisterRequestVO): AuthVO

    suspend fun forgetPasswordCheck(forgetPasswordRequestVO: ForgetPasswordRequestVO)

    suspend fun forgetPassword(forgetPasswordRequestVO: ForgetPasswordRequestVO)

}