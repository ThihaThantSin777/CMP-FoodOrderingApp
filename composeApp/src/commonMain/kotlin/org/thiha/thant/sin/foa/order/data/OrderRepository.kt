package org.thiha.thant.sin.foa.order.data


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO
import org.thiha.thant.sin.foa.order.network.api_service.OrderHistoryApiService
import org.thiha.thant.sin.foa.order.network.api_service.impl.OrderHistoryServiceImpl

object OrderRepository {
    val apiService: OrderHistoryApiService = OrderHistoryServiceImpl;

    suspend fun getOrderHistory(): List<OrderHistoryVO> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getOrderHistory();
            return@withContext response;
        }
    }
}

