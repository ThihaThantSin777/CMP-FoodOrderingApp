package org.thiha.thant.sin.foa

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.thiha.thant.sin.foa.auth.ui.ForgetPasswordRoute
import org.thiha.thant.sin.foa.auth.ui.ForgetPasswordScreen
import org.thiha.thant.sin.foa.auth.ui.LoginRoute
import org.thiha.thant.sin.foa.auth.ui.ResetPasswordRoute
import org.thiha.thant.sin.foa.auth.ui.ResetPasswordScreen
import org.thiha.thant.sin.foa.auth.ui.SignupRoute
import org.thiha.thant.sin.foa.auth.viewmodel.ForgetPasswordViewModel
import org.thiha.thant.sin.foa.auth.viewmodel.LoginViewModel
import org.thiha.thant.sin.foa.auth.viewmodel.ResetPasswordViewModel
import org.thiha.thant.sin.foa.auth.viewmodel.SignupViewModel
import org.thiha.thant.sin.foa.home.ui.CartScreen
import org.thiha.thant.sin.foa.home.ui.CheckoutScreen
import org.thiha.thant.sin.foa.home.ui.MainRoute
import org.thiha.thant.sin.foa.home.ui.OrderConfirmScreen
import org.thiha.thant.sin.foa.home.ui.RestaurantDetailsScreen
import org.thiha.thant.sin.foa.home.ui.ReviewOrderScreen
import org.thiha.thant.sin.foa.home.viewmodel.HomeViewModel
import org.thiha.thant.sin.foa.order.viewmodel.OrderHistoryViewModel
import org.thiha.thant.sin.foa.profile.ui.AboutScreen

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
                val loginViewModel = viewModel {
                    LoginViewModel()
                }
                LoginRoute(
                    viewModel = loginViewModel,
                    onTapForgetPassword = {
                        navigationController.navigate(NavRoutes.ForgetPasswordScreen)

                    }, onTapSignUp = {
                        navigationController.navigate(NavRoutes.SignupScreen)
                    }, onNavigateMainScreen = {
                        navigationController.navigate(NavRoutes.MainScreen) {
                            popUpTo(navigationController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    })
            }
            composable<NavRoutes.SignupScreen>() {
                val signUpViewModel = viewModel {
                    SignupViewModel()
                }
                SignupRoute(
                    viewModel = signUpViewModel,
                    onTapBack = {
                        navigationController.navigateUp()
                    }, onNavigateMainScreen = {
                        navigationController.navigate(NavRoutes.MainScreen) {
                            popUpTo(navigationController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    })
            }

            composable<NavRoutes.ForgetPasswordScreen>() {
                val forgetPasswordViewModel = viewModel {
                    ForgetPasswordViewModel()
                }
                ForgetPasswordRoute(
                    viewModel = forgetPasswordViewModel,
                    onTapBack = {
                        navigationController.navigateUp()
                    }, onTapContinue = { email, resetPasswordToken ->
                        println("Email: $email")
                        println("ResetPassword Token: $resetPasswordToken")
                        navigationController.navigate(
                            NavRoutes.ResetPasswordScreen(
                                email = email,
                                resetPasswordToken = resetPasswordToken,
                            )
                        )
                    })
            }
            composable<NavRoutes.ResetPasswordScreen>() { backStackEntry ->
                val args = backStackEntry.toRoute<NavRoutes.ResetPasswordScreen>()
                val email = args.email;
                val resetPasswordToken = args.resetPasswordToken;
                val resetPasswordViewModel = viewModel {
                    ResetPasswordViewModel()
                }
                ResetPasswordRoute(
                    viewModel = resetPasswordViewModel,
                    onTapBack = {
                        navigationController.navigateUp()
                    },
                    onTapResetPassword = {
                        navigationController.navigate(NavRoutes.LoginScreen)
                    },
                    email = email,
                    resetPasswordToken = resetPasswordToken,
                )
            }

            composable<NavRoutes.MainScreen>() {

                val homeViewModel = viewModel {
                    HomeViewModel()
                }

                val orderHistoryViewModel = viewModel {
                    OrderHistoryViewModel()
                }
                MainRoute(
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
                    },
                    homeViewModel = homeViewModel,
                    orderHistoryViewModel = orderHistoryViewModel,
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
    data class ResetPasswordScreen(val email: String, val resetPasswordToken: String)

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