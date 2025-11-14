package org.thiha.thant.sin.foa.order.network.api_service

import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO

interface OrderHistoryApiService {
    suspend fun getOrderHistory(): List<OrderHistoryVO>

}