package org.thiha.thant.sin.foa.auth.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.thiha.thant.sin.foa.auth.data.vos.LoginRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.AuthVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordRequestVO
import org.thiha.thant.sin.foa.auth.data.vos.ForgetPasswordVO
import org.thiha.thant.sin.foa.auth.data.vos.RegisterRequestVO
import org.thiha.thant.sin.foa.auth.network.api_service.AuthApiService
import org.thiha.thant.sin.foa.auth.network.api_service.impl.AuthApiServiceImpl
import org.thiha.thant.sin.foa.core.persistence.AppDatabaseProvider

object AuthRepository {
    val apiService: AuthApiService = AuthApiServiceImpl;
    val appDatabase = AppDatabaseProvider.appDatabase
    suspend fun login(loginRequestVO: LoginRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.login(loginRequestVO);
            appDatabase.userDao().insertUser(response)
            return@withContext response;
        }
    }

    suspend fun register(registerRequestVO: RegisterRequestVO): AuthVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.register(registerRequestVO);
            appDatabase.userDao().insertUser(response)
            return@withContext response;
        }
    }

    suspend fun forgetPasswordCheck(forgetPasswordRequestVO: ForgetPasswordRequestVO): ForgetPasswordVO {
        return withContext(Dispatchers.IO) {
            val response = apiService.forgetPasswordCheck(forgetPasswordRequestVO);
            return@withContext response;
        }
    }

    suspend fun forgetPassword(
        forgetPasswordRequestVO: ForgetPasswordRequestVO,
        resetPasswordToken: String
    ) {
        return withContext(Dispatchers.IO) {
            val response = apiService.forgetPassword(forgetPasswordRequestVO, resetPasswordToken);
            return@withContext response;
        }
    }

    suspend fun getLoggedInUser(): AuthVO? =
        withContext(Dispatchers.IO) {
            appDatabase.userDao().getUser()
        }

    suspend fun isUserLoggedIn(): Boolean =
        withContext(Dispatchers.IO) {
            appDatabase.userDao().getUser() != null
        }

    suspend fun getLoggedInUserToken(): String =
        withContext(Dispatchers.IO) {
            appDatabase.userDao().getUser()?.accessToken ?: ""
        }

    suspend fun clearInformationFromDatabase() =
        withContext(Dispatchers.IO) {
            appDatabase.userDao().clearAll()
            appDatabase.deliAddressDao().clearAll()
            appDatabase.paymentMethodDao().clearAll()
            appDatabase.foodItemDao().clearAll()
        }
}