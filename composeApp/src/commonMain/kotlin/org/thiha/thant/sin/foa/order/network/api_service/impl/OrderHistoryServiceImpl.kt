package org.thiha.thant.sin.foa.order.network.api_service.impl

import io.ktor.client.request.get
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_GET_ORDERS_FOR_USER
import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO
import org.thiha.thant.sin.foa.order.network.api_service.OrderHistoryApiService

object OrderHistoryServiceImpl : OrderHistoryApiService {
    override suspend fun getOrderHistory(): List<OrderHistoryVO> {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ORDERS_FOR_USER) {
            ///TODO Add Token
        }
        return transformResult<List<OrderHistoryVO>>(httpResponse)
    }


}