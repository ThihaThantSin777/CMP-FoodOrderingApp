package org.thiha.thant.sin.foa.home.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.core.persistence.AppDatabase
import org.thiha.thant.sin.foa.core.persistence.AppDatabaseProvider
import org.thiha.thant.sin.foa.home.data.vos.CreateOrderRequestVO
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressRequestVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantDetailsVO
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO
import org.thiha.thant.sin.foa.home.network.api_service.HomeApiService
import org.thiha.thant.sin.foa.home.network.api_service.impl.HomeApiServiceImpl

object HomeRepository {
    val apiService: HomeApiService = HomeApiServiceImpl;

    val appDatabase: AppDatabase = AppDatabaseProvider.appDatabase;

    suspend fun getAllRestaurant(): List<RestaurantVO> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getAllRestaurant()
            return@withContext response;
        }
    }

    suspend fun getRestaurantByID(id: Long): RestaurantDetailsVO {
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

    suspend fun addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO: PaymentAndAddressRequestVO): PaymentAndAddressVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.addDeliveryAddressAndPaymentMethod(paymentAndAddressRequestVO)
            return@withContext response;
        }
    }

    suspend fun submitOrder(createOrderRequestVo: CreateOrderRequestVO) {
        return withContext(Dispatchers.IO) {
            val response = apiService.submitOrder(createOrderRequestVo)
            appDatabase.deliAddressDao().clearAll();
            appDatabase.paymentMethodDao().clearAll();
            appDatabase.foodItemDao().clearAll();
            return@withContext response;
        }
    }

    suspend fun addFoodItemInDatabase(foodItemVO: FoodItemVO) {
        return withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().insertFoodItem(foodItemVO)
        }
    }

    suspend fun updateFoodItemInDatabase(foodItemVO: FoodItemVO) {
        return withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().updateFoodItem(foodItemVO)
        }
    }

    suspend fun deleteFoodItemByIDInDatabase(foodId: Long) {
        return withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().deleteFoodItem(foodId)
        }
    }

    suspend fun getFoodItemInDatabase(): List<FoodItemVO> {
        return withContext(Dispatchers.IO) {
            val response = appDatabase.foodItemDao().getAllFoodItems();
            return@withContext response;
        }
    }


    suspend fun addPaymentMethodInDatabase(paymentMethod: PaymentMethodVO) {
        return withContext(Dispatchers.IO) {
            deletePaymentMethodByIDInDataBase(paymentMethod.id)
            appDatabase.paymentMethodDao().insertPaymentMethod(paymentMethod)
        }
    }

    suspend fun deletePaymentMethodByIDInDataBase(id: Long) {
        return withContext(Dispatchers.IO) {
            appDatabase.paymentMethodDao().deletePaymentMethodByID(id)
        }
    }

    suspend fun getPaymentMethodInDatabase(): List<PaymentMethodVO> {
        return withContext(Dispatchers.IO) {
            val response = appDatabase.paymentMethodDao().getAllPaymentMethods();
            return@withContext response;
        }
    }

    suspend fun addDeliveryAddressInDatabase(deliveryAddressVO: DeliveryAddressVO) {
        return withContext(Dispatchers.IO) {
            deleteDeliveryAddressByIDInDataBase(deliveryAddressVO.id)
            appDatabase.deliAddressDao().insertDeliveryAddress(deliveryAddressVO)
        }
    }

    suspend fun deleteDeliveryAddressByIDInDataBase(id: Long) {
        return withContext(Dispatchers.IO) {
            appDatabase.deliAddressDao().deleteDeliveryAddressByID(id)
        }
    }

    suspend fun getDeliveryAddressInDatabase(): List<DeliveryAddressVO> {
        return withContext(Dispatchers.IO) {
            val response = appDatabase.deliAddressDao().getAllDeliveryAddresses();
            return@withContext response;
        }
    }
}