package org.thiha.thant.sin.foa.home.network.api_service

import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVo
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO


interface HomeApiService {
    suspend fun getAllRestaurant(): List<RestaurantVO>

    suspend fun getRestaurantByID(id: Long): RestaurantVO

    suspend fun getDeliveryAddressAndPaymentMethod(): PaymentAndAddressVO

    suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO)

    suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVo)

}