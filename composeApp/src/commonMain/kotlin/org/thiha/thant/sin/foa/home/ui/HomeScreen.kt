package org.thiha.thant.sin.foa.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.shop_cart_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onTapCart: () -> Unit, onTapRestaurant: () -> Unit) {
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
        LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {
            items(20) { _ ->
                RestaurantsNearYouCard(Modifier.clickable {
                    onTapRestaurant()
                })
                Spacer(modifier = Modifier.height(MARGIN_LARGE))

            }
        }
    }
}


@Composable
fun RestaurantsNearYouCard(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = MARGIN_CARD_MEDIUM_2),
    ) {
        AppNetworkImage(
            modifier = Modifier.fillMaxWidth().height(DEFAULT_RESTAURANT_IMAGE_HEIGHT),
            imageUrl = "https://media.istockphoto.com/id/603906484/photo/vegetable-salad.jpg?s=612x612&w=0&k=20&c=f7BnJRCqLKaj_DEQB1SB71_eRT8y1XRP52dDyYRSxuE=",
            shape = RoundedCornerShape(MARGIN_MEDIUM_3),
        )
        Spacer(
            modifier = Modifier.height(MARGIN_MEDIUM_3),
        )
        Text("Pizza Palace", fontWeight = FontWeight.W700, fontSize = TEXT_REGULAR_3X)
        Spacer(
            modifier = Modifier.height(MARGIN_SMALL),
        )
        Text(
            "Dinner, Lunche",
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
                "4.8 ⭐\uFE0F (3,300+)",
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