package org.thiha.thant.sin.foa.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmScreen(
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order Confirmed") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = MARGIN_MEDIUM),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            AppNetworkImage(
                imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?q=80&w=1600",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2)
            )

            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                text = "Your order is confirmed!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(MARGIN_MEDIUM))

            Text(
                text = "Your order will be delivered in 30–45 minutes.\nYou can track your order in the Orders tab.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(MARGIN_MEDIUM))


            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3, vertical = MARGIN_MEDIUM_2)
            ) {
                AppPrimaryButton(
                    text = "Confirm",
                    onClick = onConfirm,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DEFAULT_BUTTON_HEIGHT)
                )
            }
            Spacer(Modifier.height(MARGIN_40))
        }
    }
}
