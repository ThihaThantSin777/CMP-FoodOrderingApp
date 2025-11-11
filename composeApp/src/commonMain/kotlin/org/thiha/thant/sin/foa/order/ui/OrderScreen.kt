package org.thiha.thant.sin.foa.order.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.SubcomposeAsyncImage
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_IMAGE_LOADING_SIZE
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_IMAGE_SIZE
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.ORDER_TITLE
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X

@Composable
fun OrderScreen() {
    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
                .padding(horizontal = MARGIN_CARD_MEDIUM_2),
        ) {
            Text(ORDER_TITLE, fontSize = TEXT_LARGE_3x, fontWeight = FontWeight.W700)
            Spacer(modifier = Modifier.height(MARGIN_CARD_MEDIUM_2))
            LazyColumn {
                items(20) { _ ->
                    OrderItem()
                    Spacer(modifier = Modifier.height(MARGIN_MEDIUM_3))
                }
            }
        }
    }
}

@Composable
fun OrderItem() {
    Row {
        SubcomposeAsyncImage(
            modifier = Modifier.size(DEFAULT_ORDER_IMAGE_SIZE).clip(CircleShape),
            model = "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/chorizo-mozarella-gnocchi-bake-cropped-9ab73a3.jpg",
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier.requiredSize(
                        DEFAULT_ORDER_IMAGE_LOADING_SIZE
                    )
                )
            },
            error = {
                Icon(Icons.Default.Info, contentDescription = null)
            },
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(MARGIN_CARD_MEDIUM_2))
        Column {
            Text("Taco Fiesta", fontSize = TEXT_REGULAR_2X, fontWeight = FontWeight.W500)
            Text(
                "Tacos, Burritos, Quesadillas",
                fontSize = TEXT_REGULAR,
                fontWeight = FontWeight.W400
            )
            Text(
                "Wednesday Nov 9 · 3 items", fontSize = TEXT_REGULAR, fontWeight = FontWeight.W400
            )

        }
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painterResource(Res.drawable.chevron_right_icon),
            contentDescription = null,
            modifier = Modifier.padding(end = MARGIN_CARD_MEDIUM_2).size(
                DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
            )
        )

    }
}