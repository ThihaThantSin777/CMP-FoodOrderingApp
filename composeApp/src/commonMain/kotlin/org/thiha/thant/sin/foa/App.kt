package org.thiha.thant.sin.foa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.thiha.thant.sin.foa.auth.forget_password.ui.ForgetPasswordScreen
import org.thiha.thant.sin.foa.auth.login.ui.LoginScreen
import org.thiha.thant.sin.foa.auth.reset_password.ui.ResetPasswordScreen
import org.thiha.thant.sin.foa.auth.sign_up.ui.SignupScreen
import org.thiha.thant.sin.foa.home.cart.ui.CartScreen
import org.thiha.thant.sin.foa.home.order_confirm.ui.OrderConfirmScreen
import org.thiha.thant.sin.foa.home.restaurant_details.ui.RestaurantDetailsScreen
import org.thiha.thant.sin.foa.home.review_order.ui.ReviewOrderScreen
import org.thiha.thant.sin.foa.home.ui.HomeScreen
import org.thiha.thant.sin.foa.order.ui.OrderScreen
import org.thiha.thant.sin.foa.profile.about.ui.AboutScreen
import org.thiha.thant.sin.foa.profile.ui.ProfileScreen

@Composable
@Preview
fun App() {

    val navigationController = rememberNavController()
    val focusManager = LocalFocusManager.current;
    val interactionSource = remember { MutableInteractionSource() }

    MaterialTheme {
        NavHost(
            navigationController,
            startDestination = NavRoutes.LoginScreen,
            modifier = Modifier.clickable(
                interactionSource = interactionSource, indication = null
            ) {
                focusManager.clearFocus()
            }) {
            composable<NavRoutes.LoginScreen>() {
                LoginScreen(onTapForgetPassword = {
                    navigationController.navigate(NavRoutes.ForgetPasswordScreen)

                }, onTapSignUp = {
                    navigationController.navigate(NavRoutes.SignupScreen)
                }, onTapLogin = {})
            }
            composable<NavRoutes.SignupScreen>() {
                SignupScreen(onTapBack = {
                    navigationController.navigateUp()
                }, onTapCreateAccount = {})
            }

            composable<NavRoutes.ForgetPasswordScreen>() {
                ForgetPasswordScreen(onTapBack = {
                    navigationController.navigateUp()
                }, onTapContinue = {
                    navigationController.navigate(NavRoutes.ResetPasswordScreen)
                })
            }
            composable<NavRoutes.ResetPasswordScreen>() {
                ResetPasswordScreen(onTapBack = {
                    navigationController.navigateUp()
                }, onTapResetPassword = {})
            }

            composable<NavRoutes.HomeScreen>() {
                HomeScreen()
            }

            composable<NavRoutes.RestaurantDetailsScreen>() { backStackEntry ->
                val args = backStackEntry.toRoute<NavRoutes.RestaurantDetailsScreen>()
                val restaurantID = args.restaurantID;
                RestaurantDetailsScreen(
                    restaurantId = restaurantID
                )
            }

            composable<NavRoutes.CartScreen>() {
                CartScreen()
            }

            composable<NavRoutes.ReviewOrderScreen>() {
                ReviewOrderScreen()
            }

            composable<NavRoutes.OrderConfirmScreen>() {
                OrderConfirmScreen()
            }

            composable<NavRoutes.OrderScreen>() {
                OrderScreen()
            }

            composable<NavRoutes.ProfileScreen>() {
                ProfileScreen()
            }

            composable<NavRoutes.AboutScreen>() {
                AboutScreen()
            }
        }
    }
}

@Serializable
sealed class NavRoutes {


    //Auth
    @Serializable
    object LoginScreen

    @Serializable
    object SignupScreen

    @Serializable
    object ForgetPasswordScreen

    @Serializable
    object ResetPasswordScreen


    //Home
    @Serializable
    object HomeScreen

    @Serializable
    data class RestaurantDetailsScreen(val restaurantID: Int)

    @Serializable
    object CartScreen

    @Serializable
    object ReviewOrderScreen

    @Serializable
    object OrderConfirmScreen


    //Order
    @Serializable
    object OrderScreen

    //Profile
    @Serializable
    object ProfileScreen

    @Serializable
    object AboutScreen
}