package org.thiha.thant.sin.foa.home.network.api_service

import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantDetailsVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO


interface HomeApiService {
    suspend fun getAllRestaurant(): List<RestaurantVO>

    suspend fun getRestaurantByID(id: Long): RestaurantDetailsVO

    suspend fun getDeliveryAddressAndPaymentMethod(): PaymentAndAddressVO

    suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO): PaymentAndAddressVO

    suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVO)

}