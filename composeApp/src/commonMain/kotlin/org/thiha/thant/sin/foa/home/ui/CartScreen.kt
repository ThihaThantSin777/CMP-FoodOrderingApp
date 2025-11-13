package org.thiha.thant.sin.foa.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.CART_IMAGE_SIZE
import org.thiha.thant.sin.foa.core.CART_TITLE
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_SMALL
import org.thiha.thant.sin.foa.core.PLACE_ORDER_TITLE
import org.thiha.thant.sin.foa.core.TOTAL_TITLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {}
) {
    var cart by remember {
        mutableStateOf(
            listOf(
                CartItemVM(
                    id = 1,
                    title = "Spicy Chicken Sandwich",
                    price = 12.99,
                    image = "https://media.istockphoto.com/id/603906484/photo/vegetable-salad.jpg?s=612x612&w=0&k=20&c=f7BnJRCqLKaj_DEQB1SB71_eRT8y1XRP52dDyYRSxuE=",
                    qty = 1
                ),
                CartItemVM(
                    id = 2,
                    title = "Fries",
                    price = 3.99,
                    image = "https://media.istockphoto.com/id/603906484/photo/vegetable-salad.jpg?s=612x612&w=0&k=20&c=f7BnJRCqLKaj_DEQB1SB71_eRT8y1XRP52dDyYRSxuE=",
                    qty = 1
                ),
                CartItemVM(
                    id = 3,
                    title = "Coke",
                    price = 2.49,
                    image = "https://media.istockphoto.com/id/603906484/photo/vegetable-salad.jpg?s=612x612&w=0&k=20&c=f7BnJRCqLKaj_DEQB1SB71_eRT8y1XRP52dDyYRSxuE=",
                    qty = 1
                )
            )
        )
    }
    val total = cart.sumOf { it.price * it.qty }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(CART_TITLE) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        ) {
            // Cart list
            Column(Modifier.fillMaxWidth()) {
                cart.forEach { item ->
                    CartRow(
                        item = item,
                        onMinus = {
                            cart = cart.map {
                                if (it.id == item.id) it.copy(qty = (it.qty - 1).coerceAtLeast(0)) else it
                            }
                        },
                        onPlus = {
                            cart = cart.map {
                                if (it.id == item.id) it.copy(qty = it.qty + 1) else it
                            }
                        }
                    )
                }
            }

            HorizontalDivider(
                Modifier.padding(vertical = MARGIN_MEDIUM).padding(MARGIN_CARD_MEDIUM_2)
            )

            // Total row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_2, vertical = MARGIN_MEDIUM),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    TOTAL_TITLE,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$$total",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(Modifier.height(MARGIN_CARD_MEDIUM_2))

            // Place order button
            AppPrimaryButton(
                onClick = onPlaceOrder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_2),
                text = PLACE_ORDER_TITLE,
            )
            Spacer(Modifier.height(MARGIN_MEDIUM_2))
        }
    }
}

@Composable
private fun CartRow(
    item: CartItemVM,
    onMinus: () -> Unit,
    onPlus: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MARGIN_MEDIUM_2, vertical = MARGIN_CARD_MEDIUM_2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        AppNetworkImage(
            imageUrl = item.image,
            modifier = Modifier
                .size(CART_IMAGE_SIZE),
            shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2)
        )

        Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))

        // Title + stepper
        Column(modifier = Modifier.weight(1f)) {
            Text(
                item.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(MARGIN_SMALL))
            Row(verticalAlignment = Alignment.CenterVertically) {
                StepperButton(text = "−", onClick = onMinus)
                Text(
                    text = item.qty.toString(),
                    modifier = Modifier.padding(horizontal = MARGIN_CARD_MEDIUM_2),
                    style = MaterialTheme.typography.bodyLarge
                )
                StepperButton(text = "+", onClick = onPlus)
            }
        }

        // Price right-aligned
        Text(
            text = "$${item.price}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun StepperButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(MARGIN_LARGE)
            .border(1.5.dp, MaterialTheme.colorScheme.error, CircleShape)
            .background(MaterialTheme.colorScheme.surface, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.SemiBold)
    }
}


data class CartItemVM(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val qty: Int
)
