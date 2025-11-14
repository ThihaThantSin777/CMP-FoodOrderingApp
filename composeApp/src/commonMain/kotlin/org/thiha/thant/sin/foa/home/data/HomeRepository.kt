package org.thiha.thant.sin.foa.home.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVo
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO
import org.thiha.thant.sin.foa.home.network.api_service.HomeApiService
import org.thiha.thant.sin.foa.home.network.api_service.impl.HomeApiServiceImpl

object HomeRepository {
    val apiService: HomeApiService = HomeApiServiceImpl;

    suspend fun getAllRestaurant(): List<RestaurantVO> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllRestaurant()
            return@withContext response;
        }
    }

    suspend fun getRestaurantByID(id: Long): RestaurantVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.getRestaurantByID(id)
            return@withContext response;
        }
    }

    suspend fun getDeliveryAddressAndPaymentMethod(): PaymentAndAddressVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.getDeliveryAddressAndPaymentMethod()
            return@withContext response;
        }
    }

    suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO) {
        return withContext(Dispatchers.IO) {
            val response = apiService.addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO)
            return@withContext response;
        }
    }

    suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVo) {
        return withContext(Dispatchers.IO) {
            val response = apiService.submitOrder(createOrderRequestVo)
            return@withContext response;
        }
    }
}