package org.thiha.thant.sin.foa.order.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppErrorView
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.core.DEFAULT_DIVIDER_EXTRA_HEIGHT
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_IMAGE_SIZE
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.ORDER_HISTORY_DESC_TEXT
import org.thiha.thant.sin.foa.core.ORDER_HISTORY_EMPTY_TITLE_TEXT
import org.thiha.thant.sin.foa.core.ORDER_TITLE
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.order.data.vos.OrderHistoryVO
import org.thiha.thant.sin.foa.order.state.OrderHistoryState
import org.thiha.thant.sin.foa.order.viewmodel.OrderHistoryViewModel

@Composable
fun OrderHistoryRoute(viewModel: OrderHistoryViewModel) {
    val orderHistoryState by viewModel.state.collectAsStateWithLifecycle()
    OrderHistoryScreen(
        orderHistoryState = orderHistoryState,
        onTapRetry = { viewModel.loadOrderHistory() }
    )
}

@Composable
fun OrderHistoryScreen(
    orderHistoryState: OrderHistoryState,
    onTapRetry: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = MARGIN_CARD_MEDIUM_2),
        ) {
            Text(
                ORDER_TITLE,
                fontSize = TEXT_LARGE_3x,
                fontWeight = FontWeight.W700
            )

            Spacer(modifier = Modifier.height(MARGIN_MEDIUM_3))

            when (orderHistoryState.uiState) {
                UiState.LOADING -> {
                    AppLoadingDialog()
                }

                UiState.FAIL -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppErrorView(
                            message = orderHistoryState.errorMessage,
                            onTapRetry = onTapRetry
                        )
                    }
                }

                else -> {
                    val orderHistoryList = orderHistoryState.orderHistory
                    if (orderHistoryList.isNotEmpty()) {
                        LazyColumn {
                            items(orderHistoryList.size) { index ->
                                OrderItem(orderHistoryVO = orderHistoryList[index])
                                val isLastItem = index == orderHistoryList.lastIndex
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
                    } else {
                        EmptyOrderHistoryView()
                    }
                }
            }
        }
    }
}

@Composable
fun OrderItem(orderHistoryVO: OrderHistoryVO) {
    Row {
        AppNetworkImage(
            modifier = Modifier.size(DEFAULT_ORDER_IMAGE_SIZE),
            imageUrl = orderHistoryVO.foodItems.first().imageUrl,
            shape = CircleShape,
        )

        Spacer(modifier = Modifier.width(MARGIN_CARD_MEDIUM_2))

        Column {
            Text(
                orderHistoryVO.foodItems.first().name,
                fontSize = TEXT_REGULAR_2X,
                fontWeight = FontWeight.W500
            )
            Text(
                orderHistoryVO.foodItems.joinToString(",") { it.name },
                fontSize = TEXT_REGULAR,
                fontWeight = FontWeight.W400
            )
            Text(
                "${orderHistoryVO.formateDate()} · ${orderHistoryVO.itemCount()}",
                fontSize = TEXT_REGULAR,
                fontWeight = FontWeight.W400
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painterResource(Res.drawable.chevron_right_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = MARGIN_CARD_MEDIUM_2)
                .size(DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE)
        )
    }
}

@Composable
private fun EmptyOrderHistoryView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MARGIN_LARGE),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = PRIMARY_COLOR,
            modifier = Modifier.size(DEFAULT_ORDER_IMAGE_SIZE)
        )
        Spacer(Modifier.height(MARGIN_MEDIUM_3))
        Text(
            text = ORDER_HISTORY_EMPTY_TITLE_TEXT,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(MARGIN_CARD_MEDIUM_2))
        Text(
            text = ORDER_HISTORY_DESC_TEXT,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(MARGIN_LARGE))
    }
}
