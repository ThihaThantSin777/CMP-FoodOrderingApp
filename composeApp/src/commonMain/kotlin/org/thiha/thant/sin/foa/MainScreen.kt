package org.thiha.thant.sin.foa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.home_select_icon
import foodorderingapp.composeapp.generated.resources.order_select_icon
import foodorderingapp.composeapp.generated.resources.profile_select_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.core.DEFAULT_BOTTOM_NAV_BAR_SIZE
import org.thiha.thant.sin.foa.core.HOME_ROUTE
import org.thiha.thant.sin.foa.core.ORDER_ROUTE
import org.thiha.thant.sin.foa.core.PROFILE_ROUTE
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.home.ui.HomeScreen
import org.thiha.thant.sin.foa.order.ui.OrderScreen
import org.thiha.thant.sin.foa.profile.ui.ProfileScreen

@Composable
fun MainScreen(desireRoute: String = ORDER_ROUTE) {
    var selectedItem by remember {
        mutableStateOf(desireRoute)
    }
    val bottomNavigationItems = listOf(
        BottomNavItem(
            label = HOME_ROUTE,
            selectedImage = Res.drawable.home_select_icon,
        ),
        BottomNavItem(
            label = ORDER_ROUTE,
            selectedImage = Res.drawable.order_select_icon,
        ),
        BottomNavItem(
            label = PROFILE_ROUTE,
            selectedImage = Res.drawable.profile_select_icon,
        ),
    );
    Scaffold(containerColor = Color.White,bottomBar = {
        Column {
            HorizontalDivider()
            NavigationBar(containerColor = Color.White) {
                bottomNavigationItems.forEach {
                    val isSelected = selectedItem == it.label
                    NavigationBarItem(
                        selected = isSelected,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            selectedTextColor = Color.Black,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = SECONDARY_COLOR,
                            unselectedTextColor = SECONDARY_COLOR
                        ),
                        icon = {
                            Icon(
                                painterResource(it.selectedImage),
                                contentDescription = null,
                                modifier = Modifier.size(DEFAULT_BOTTOM_NAV_BAR_SIZE)
                            )
                        },
                        label = {
                            Text(
                                it.label,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        onClick = {
                            selectedItem = it.label
                        },
                    )
                }
            }
        }
    }) {
        when (selectedItem) {
            HOME_ROUTE -> {
                HomeScreen()
            }

            ORDER_ROUTE -> {
                OrderScreen()
            }

            PROFILE_ROUTE -> {
                ProfileScreen()
            }

            else -> {
                HomeScreen()
            }
        }
    }

}

data class BottomNavItem(
    val label: String,
    val selectedImage: DrawableResource,
)

