package org.thiha.thant.sin.foa.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.shop_cart_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppErrorView
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.DEFAULT_DIVIDER_EXTRA_HEIGHT
import org.thiha.thant.sin.foa.core.DEFAULT_RESTAURANT_IMAGE_HEIGHT
import org.thiha.thant.sin.foa.core.HOME_PAGE_ORDER_BUTTON_HEIGHT
import org.thiha.thant.sin.foa.core.HOME_PAGE_ORDER_BUTTON_WIDTH
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.MARGIN_SMALL
import org.thiha.thant.sin.foa.core.ORDER_BUTTON_TEXT
import org.thiha.thant.sin.foa.core.RESTAURANT_NEAR_YOU_TITLE
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_LARGE
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_3X
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.RestaurantVO
import org.thiha.thant.sin.foa.home.state.HomeState
import org.thiha.thant.sin.foa.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(viewModel: HomeViewModel, onTapCart: () -> Unit, onTapRestaurant: () -> Unit) {

    val homeState by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        onTapRestaurant = onTapRestaurant,
        onTapCart = onTapCart,
        state = homeState,
        onTapRetry = {
            viewModel.loadRestaurants()
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onTapCart: () -> Unit,
    onTapRestaurant: () -> Unit,
    onTapRetry: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    RESTAURANT_NEAR_YOU_TITLE,
                    fontSize = TEXT_LARGE,
                    fontWeight = FontWeight.W700
                )
            },
            actions = {
                Image(
                    painterResource(Res.drawable.shop_cart_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(end = MARGIN_CARD_MEDIUM_2).clickable {
                        onTapCart()
                    }
                )
            }
        )
    }) {
        when (state.uiState) {
            UiState.LOADING -> {
                AppLoadingDialog()
            }

            UiState.FAIL -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    AppErrorView(state.errorMessage, onTapRetry = onTapRetry)
                }
            }

            else -> {
                val restaurantList = state.restaurantList;
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    if (restaurantList.isNotEmpty()) {

                        items(restaurantList.size) { index ->

                            RestaurantsNearYouCard(
                                restaurant = restaurantList[index],
                                modifier = Modifier.clickable { onTapRestaurant() }
                            )

                            val isLastItem = index == restaurantList.lastIndex

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(
                                        if (isLastItem) DEFAULT_DIVIDER_EXTRA_HEIGHT
                                        else MARGIN_LARGE
                                    )
                            )
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun RestaurantsNearYouCard(modifier: Modifier, restaurant: RestaurantVO) {
    Column(
        modifier = modifier
            .padding(horizontal = MARGIN_CARD_MEDIUM_2),
    ) {
        AppNetworkImage(
            modifier = Modifier.fillMaxWidth().height(DEFAULT_RESTAURANT_IMAGE_HEIGHT),
            imageUrl = restaurant.imageUrl,
            shape = RoundedCornerShape(MARGIN_MEDIUM_3),
        )
        Spacer(
            modifier = Modifier.height(MARGIN_MEDIUM_3),
        )
        Text(restaurant.name, fontWeight = FontWeight.W700, fontSize = TEXT_REGULAR_3X)
        Spacer(
            modifier = Modifier.height(MARGIN_SMALL),
        )
        Text(
            restaurant.restaurantCategories.map {
                it.name
            }.toList().joinToString(","),
            fontWeight = FontWeight.W400,
            fontSize = TEXT_REGULAR_2X,
            color = SECONDARY_COLOR,
        )
        Spacer(
            modifier = Modifier.height(MARGIN_SMALL),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "${restaurant.averageRating} ⭐\uFE0F (3,300+)",
                fontWeight = FontWeight.W400,
                fontSize = TEXT_REGULAR_2X,
                color = SECONDARY_COLOR,
            )
            Spacer(
                modifier = Modifier.weight(1F),
            )
            AppPrimaryButton(
                modifier = Modifier.width(HOME_PAGE_ORDER_BUTTON_WIDTH).height(
                    HOME_PAGE_ORDER_BUTTON_HEIGHT
                ),
                text = ORDER_BUTTON_TEXT,
                onClick = {}
            )
        }
    }
}