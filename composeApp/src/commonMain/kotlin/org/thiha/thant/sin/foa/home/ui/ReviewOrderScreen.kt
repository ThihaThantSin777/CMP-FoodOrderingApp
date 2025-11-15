package org.thiha.thant.sin.foa.home.ui

import androidx.compose.runtime.Composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.credit_card_image
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppDialog
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.*
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.FoodItemVO
import org.thiha.thant.sin.foa.home.state.ReviewOrderState
import org.thiha.thant.sin.foa.home.viewmodel.ReviewOrderViewModel

@Composable
fun ReviewOrderRoute(
    viewModel: ReviewOrderViewModel,
    onBack: () -> Unit = {},
    onConfirmOrder: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showErrorDialog by remember { mutableStateOf(false) }

    LaunchedEffect(state.uiState) {
        if (state.uiState == UiState.SUCCESS) {
            onConfirmOrder();
        }

        if (state.uiState == UiState.FAIL) {
            showErrorDialog = true
        }
    }

    ReviewOrderScreen(
        onBack = onBack,
        state = state,
        showErrorDialog = showErrorDialog,
        onTapOKButtonDialog = {
            showErrorDialog = false;
        },
        onConfirmOrder = {
            viewModel.onTapConfirmOrder()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewOrderScreen(
    onBack: () -> Unit = {},
    onConfirmOrder: () -> Unit = {},
    state: ReviewOrderState,
    showErrorDialog: Boolean,
    onTapOKButtonDialog: () -> Unit,
) {
    val orderItems: List<FoodItemVO> = state.orderItems
    val addressDetails = state.deliveryAddressVO?.streetAddress ?: "-"
    val paymentMethod = state.paymentMethodVO

    val paymentType = PAYMENT_METHOD_TITLE
    val maskedCard = paymentMethod?.cardNumber
        ?.takeLast(4)
        ?.let { "**** **** **** $it" }
        ?: "-"

    val total = orderItems.sumOf { it.price * it.quantity }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(REVIEW_ORDER_TITLE) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3, vertical = MARGIN_MEDIUM_2)
            ) {
                AppPrimaryButton(
                    text = CONFIRM_ORDER_TITLE,
                    onClick = onConfirmOrder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DEFAULT_BUTTON_HEIGHT)
                )
            }
        }
    ) { inner ->
        if (state.uiState == UiState.LOADING) {
            AppLoadingDialog()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = MARGIN_MEDIUM_3)
        ) {
            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                ORDER_SUMMARY_TITLE,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = MARGIN_MEDIUM)
            )

            orderItems.forEach { item ->
                ReviewOrderRow(item)
            }

            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                DELIVERY_ADDRESS_TITLE,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_REGULAR_3X,
                modifier = Modifier.padding(bottom = MARGIN_MEDIUM)
            )
            Spacer(Modifier.height(MARGIN_MEDIUM_3))
            Column {
                Text(addressDetails, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                PAYMENT_METHOD_TITLE,
                fontWeight = FontWeight.SemiBold,
                fontSize = TEXT_REGULAR_3X,
                modifier = Modifier.padding(bottom = MARGIN_MEDIUM)
            )
            Spacer(Modifier.height(MARGIN_MEDIUM_3))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(Res.drawable.credit_card_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(CREDIT_CARD_IMAGE_SIZE)
                        .padding(end = MARGIN_MEDIUM)
                )

                Column {
                    Text(paymentType, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(MARGIN_SMALL))
                    Text(maskedCard, color = SECONDARY_COLOR)
                }
            }

            Spacer(Modifier.height(MARGIN_MEDIUM_3))

            Text(
                ORDER_TITLE_TEXT,
                fontSize = TEXT_REGULAR_3X,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = MARGIN_MEDIUM)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = MARGIN_MEDIUM_2,
                        bottom = MARGIN_MEDIUM_3
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(TOTAL_TITLE_TEXT, color = SECONDARY_COLOR)
                Text(
                    "$$total",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(MARGIN_MEDIUM_2))

            if (showErrorDialog) {
                AppDialog(
                    title = SUBMIT_ORDER_FAIL_TITLE,
                    message = state.errorMessage,
                    confirmText = OK_TEXT,
                    onConfirm = { onTapOKButtonDialog() },
                    onDismissRequest = { onTapOKButtonDialog() },
                )
            }
        }
    }
}

@Composable
fun ReviewOrderRow(item: FoodItemVO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MARGIN_MEDIUM),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppNetworkImage(
            imageUrl = item.imageUrl,
            modifier = Modifier
                .size(REVIEW_ORDER_IMAGE_SIZE),
            shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2)
        )

        Spacer(Modifier.width(MARGIN_MEDIUM_2))

        Column(Modifier.weight(1f)) {
            Text(item.name, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(MARGIN_SMALL))
            Text("${item.quantity}×", color = SECONDARY_COLOR)
        }

        Text(
            "$${item.price}",
            fontWeight = FontWeight.Medium
        )
    }
}
