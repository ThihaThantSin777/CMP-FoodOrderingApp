package org.thiha.thant.sin.foa.home.network.api_service.impl

import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import org.thiha.thant.sin.foa.auth.data.AuthRepository
import org.thiha.thant.sin.foa.core.network.HttpClientProvider
import org.thiha.thant.sin.foa.core.network.transformResult
import org.thiha.thant.sin.foa.core.utils.API_ADD_ADDRESS_PAYMENT
import org.thiha.thant.sin.foa.core.utils.API_GET_ADDRESSES_PAYMENT
import org.thiha.thant.sin.foa.core.utils.API_GET_ALL_RESTAURANTS
import org.thiha.thant.sin.foa.core.utils.API_GET_RESTAURANT_DETAILS
import org.thiha.thant.sin.foa.core.utils.API_SUBMIT_ORDER
import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantDetailsVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO
import org.thiha.thant.sin.foa.home.network.api_service.HomeApiService


object HomeApiServiceImpl : HomeApiService {
    val authRepository: AuthRepository = AuthRepository;
    override suspend fun getAllRestaurant(): List<RestaurantVO> {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ALL_RESTAURANTS) {
            header(HttpHeaders.Authorization, "Bearer ${authRepository.getLoggedInUserToken()}")
        }
        return transformResult<List<RestaurantVO>>(httpResponse)
    }

    override suspend fun getRestaurantByID(id: Long): RestaurantDetailsVO {
        val httpResponse = HttpClientProvider.httpClient.get("$API_GET_RESTAURANT_DETAILS/$id") {
            header(HttpHeaders.Authorization, "Bearer ${authRepository.getLoggedInUserToken()}")
        }
        return transformResult<RestaurantDetailsVO>(httpResponse)
    }

    override suspend fun getDeliveryAddressAndPaymentMethod(): PaymentAndAddressVO {
        val httpResponse = HttpClientProvider.httpClient.get(API_GET_ADDRESSES_PAYMENT) {
            header(HttpHeaders.Authorization, "Bearer ${authRepository.getLoggedInUserToken()}")
        }
        return transformResult<PaymentAndAddressVO>(httpResponse)
    }

    override suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO): PaymentAndAddressVO {
        val httpResponse = HttpClientProvider.httpClient.post(API_ADD_ADDRESS_PAYMENT) {
            header(HttpHeaders.Authorization, "Bearer ${authRepository.getLoggedInUserToken()}")

            setBody(paymentAndAddressRequestVO)
        }
        return transformResult<PaymentAndAddressVO>(httpResponse)
    }

    override suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVO) {
        val httpResponse = HttpClientProvider.httpClient.post(API_SUBMIT_ORDER) {
            header(HttpHeaders.Authorization, "Bearer ${authRepository.getLoggedInUserToken()}")
            setBody(createOrderRequestVo)
        }
        return transformResult<Unit>(httpResponse)
    }


}