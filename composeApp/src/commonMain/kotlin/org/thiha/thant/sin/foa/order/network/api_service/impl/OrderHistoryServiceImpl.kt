package org.thiha.thant.sin.foa.order.network.api_service.impl

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_GET_ORDERS_FOR_USER
import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO
import org.thiha.thant.sin.foa.order.network.api_service.OrderHistoryApiService

object OrderHistoryServiceImpl : OrderHistoryApiService {
    val authRepository: AuthRepository = AuthRepository;
    override suspend fun getOrderHistory(): List<OrderHistoryVO> {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ORDERS_FOR_USER) {
            header(
                HttpHeaders.Authorization,
                "Bearer ${authRepository.getLoggedInUserToken()}"
            )
        }
        return transformResult<List<OrderHistoryVO>>(httpResponse)
    }


}