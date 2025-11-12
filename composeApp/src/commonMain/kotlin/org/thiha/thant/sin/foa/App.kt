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
import org.thiha.thant.sin.foa.home.checkout.ui.CheckoutScreen
import org.thiha.thant.sin.foa.home.order_confirm.ui.OrderConfirmScreen
import org.thiha.thant.sin.foa.home.restaurant_details.ui.RestaurantDetailsScreen
import org.thiha.thant.sin.foa.home.review_order.ui.ReviewOrderScreen

import org.thiha.thant.sin.foa.profile.about.ui.AboutScreen

@Composable
@Preview
fun App() {

    val navigationController = rememberNavController()
    val focusManager = LocalFocusManager.current;
    val interactionSource = remember { MutableInteractionSource() }

    MaterialTheme {
        NavHost(
            navigationController,
            startDestination = NavRoutes.ReviewOrderScreen,
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
                }, onTapLogin = {
                    navigationController.navigate(NavRoutes.MainScreen) {
                        popUpTo(navigationController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                })
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

            composable<NavRoutes.MainScreen>() {
                MainScreen(
                    onTapAboutScreen = {
                        navigationController.navigate(NavRoutes.AboutScreen)
                    },
                    onTapLogout = {
                        navigationController.navigate(NavRoutes.LoginScreen) {
                            popUpTo(navigationController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    },
                    onTapCart = {
                        navigationController.navigate(NavRoutes.CartScreen)
                    },
                    onTapRestaurant = {
                        navigationController.navigate(NavRoutes.RestaurantDetailsScreen(1))
                    }
                )
            }


            composable<NavRoutes.RestaurantDetailsScreen>() { backStackEntry ->
                val args = backStackEntry.toRoute<NavRoutes.RestaurantDetailsScreen>()
                val restaurantID = args.restaurantID;
                RestaurantDetailsScreen(
                    restaurantId = restaurantID,
                    onTapViewMyCart = {
                        navigationController.navigate(NavRoutes.CartScreen)

                    },
                    onTapBack = {
                        navigationController.navigateUp()

                    }
                )
            }

            composable<NavRoutes.CartScreen>() {
                CartScreen(
                    onBack = {
                        navigationController.navigateUp()
                    },
                    onPlaceOrder = {
                        navigationController.navigate(NavRoutes.CheckOutScreen)
                    },
                )
            }


            composable<NavRoutes.CheckOutScreen>() {
                CheckoutScreen(
                    onPlaceOrder = {
                        navigationController.navigate(NavRoutes.ReviewOrderScreen)
                    },
                    onBack = {
                        navigationController.navigateUp()
                    },
                )
            }

            composable<NavRoutes.ReviewOrderScreen>() {
                ReviewOrderScreen(
                    onBack = {
                        navigationController.navigateUp()
                    },
                    onConfirmOrder = {
                        navigationController.navigate(NavRoutes.OrderConfirmScreen)
                    }
                )
            }

            composable<NavRoutes.OrderConfirmScreen>() {
                OrderConfirmScreen(
                    onBack = {
                        navigationController.navigateUp()
                    },
                    onConfirm = {
                        navigationController.navigate(NavRoutes.MainScreen) {
                            popUpTo(navigationController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
            }


            composable<NavRoutes.AboutScreen>() {
                AboutScreen(
                    onBack = {
                        navigationController.navigateUp()
                    }
                )
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

    //Main
    @Serializable
    object MainScreen


    @Serializable
    data class RestaurantDetailsScreen(val restaurantID: Int)

    @Serializable
    object CartScreen

    @Serializable
    object CheckOutScreen

    @Serializable
    object ReviewOrderScreen

    @Serializable
    object OrderConfirmScreen

    @Serializable
    object AboutScreen
}