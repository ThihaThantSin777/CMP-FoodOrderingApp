package org.thiha.thant.sin.foa.home.network.api_service.impl

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import org.thiha.thant.sin.foa.State
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_ADD_ADDRESS_PAYMENT
import org.thiha.thant.sin.foa.core.utils.API_GET_ADDRESSES_PAYMENT
import org.thiha.thant.sin.foa.core.utils.API_GET_ALL_RESTAURANTS
import org.thiha.thant.sin.foa.core.utils.API_GET_RESTAURANT_DETAILS
import org.thiha.thant.sin.foa.core.utils.API_SUBMIT_ORDER
import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVo
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO
import org.thiha.thant.sin.foa.home.network.api_service.HomeApiService


object HomeApiServiceImpl : HomeApiService {
    override suspend fun getAllRestaurant(): List<RestaurantVO> {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ALL_RESTAURANTS) {
            ///TODO Need to Delect After implement Persistent
            header(HttpHeaders.Authorization, "Bearer ${State.token}")
        }
        return transformResult<List<RestaurantVO>>(httpResponse)
    }

    override suspend fun getRestaurantByID(id: Long): RestaurantVO {
        val httpResponse = HttpClientProvider.httpClient.get("$API_GET_RESTAURANT_DETAILS/$id") {
            ///TODO Need to Delect After implement Persistent
            header(HttpHeaders.Authorization, "Bearer ${State.token}")
        }
        return transformResult<RestaurantVO>(httpResponse)
    }

    override suspend fun getDeliveryAddressAndPaymentMethod(): PaymentAndAddressVO {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ADDRESSES_PAYMENT) {
            ///TODO Need to Delect After implement Persistent
            header(HttpHeaders.Authorization, "Bearer ${State.token}")
        }
        return transformResult<PaymentAndAddressVO>(httpResponse)
    }

    override suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO): Unit {
        val httpResponse = HttpClientProvider.httpClient.post(API_ADD_ADDRESS_PAYMENT) {
            ///TODO Need to Delect After implement Persistent
            header(HttpHeaders.Authorization, "Bearer ${State.token}")

            setBody(paymentAndAddressRequestVO)
        }
        return transformResult<Unit>(httpResponse)
    }

    override suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVo) {
        val httpResponse = HttpClientProvider.httpClient.post(API_SUBMIT_ORDER) {
            ///TODO Need to Delect After implement Persistent
            header(HttpHeaders.Authorization, "Bearer ${State.token}")
            setBody(createOrderRequestVo)
        }
        return transformResult<Unit>(httpResponse)
    }


}