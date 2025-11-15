package org.thiha.thant.sin.foa.home.ui

import androidx.compose.foundation.Image
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
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.order_confirm_image
import org.jetbrains.compose.resources.painterResource
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
                title = { Text(ORDER_CONFIRMED_TEXT) },
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

            Image(
                painterResource(Res.drawable.order_confirm_image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DEFAULT_ORDER_CONFIRM_IMAGE_HEIGHT)
                    .padding(MARGIN_CARD_MEDIUM_2),
            )

            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                text = ORDER_CONFIRMED_DESC,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(MARGIN_MEDIUM))

            Text(
                text = ORDER_CONFIRMED_SUB_DESC,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(MARGIN_MEDIUM))


            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3, vertical = MARGIN_MEDIUM_2)
            ) {
                AppPrimaryButton(
                    text = CONFIRM_TEXT,
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
