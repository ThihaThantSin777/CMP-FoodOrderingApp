package org.thiha.thant.sin.foa.auth.network.api_service.impl

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.network.api_service.ApiService
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_LOGIN

object ApiServiceImpl : ApiService {

    override suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        val httpResponse = HttpClientProvider.httpClient.post(API_LOGIN) {
            setBody(loginRequestVO)
        }

        return transformResult<AuthVO>(httpResponse)

    }
}