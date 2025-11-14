package org.thiha.thant.sin.foa.home.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.thiha.thant.sin.foa.components.AppDialog
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppNetworkImage
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.ADD_NEW_INFORMATION
import org.thiha.thant.sin.foa.core.CARD_DELIVERY_ADDRESS_TEXT
import org.thiha.thant.sin.foa.core.CART_IMAGE_SIZE
import org.thiha.thant.sin.foa.core.CART_TITLE
import org.thiha.thant.sin.foa.core.CHECK_OUT_INFORMATION_DESCRIPTION_TEXT
import org.thiha.thant.sin.foa.core.CHECK_OUT_INFORMATION_TEXT
import org.thiha.thant.sin.foa.core.CONTINUE_TEXT
import org.thiha.thant.sin.foa.core.DEFAULT_BUTTON_HEIGHT
import org.thiha.thant.sin.foa.core.DELIVERY_ADDRESS_AND_PAYMENT_METHOD_ERROR_TITLE
import org.thiha.thant.sin.foa.core.DELIVERY_ADDRESS_NO_SAVE_TEXT
import org.thiha.thant.sin.foa.core.MARGIN_40
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.MARGIN_SMALL
import org.thiha.thant.sin.foa.core.MARGIN_XLARGE
import org.thiha.thant.sin.foa.core.MARGIN_XSMALL
import org.thiha.thant.sin.foa.core.OK_TEXT
import org.thiha.thant.sin.foa.core.PAYMENT_METHODS_TITLE
import org.thiha.thant.sin.foa.core.PAYMENT_METHOD_NO_SAVE_TEXT
import org.thiha.thant.sin.foa.core.PLACE_ORDER_TITLE
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_3X
import org.thiha.thant.sin.foa.core.TEXT_SMALL
import org.thiha.thant.sin.foa.core.TOTAL_TITLE
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.home.data.vos.DeliveryAddressVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentAndAddressVO
import org.thiha.thant.sin.foa.home.data.vos.PaymentMethodVO
import org.thiha.thant.sin.foa.home.state.CartState
import org.thiha.thant.sin.foa.home.viewmodel.CartViewModel


@Composable
fun CartRoute(
    viewModel: CartViewModel,
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {},
    onTapNewCreateInformation: () -> Unit = {},
) {
    val cartState by viewModel.state.collectAsStateWithLifecycle()
    var showErrorDialog by remember { mutableStateOf(false) }
    var showPaymentMethodAndDeliveryAddressDialog by remember { mutableStateOf(false) }

    LaunchedEffect(cartState.uiState) {
        if (cartState.uiState == UiState.SUCCESS) {
            showPaymentMethodAndDeliveryAddressDialog = true
        }

        if (cartState.uiState == UiState.FAIL) {
            showErrorDialog = true
        }
    }

    CartScreen(
        onBack = onBack,
        onPlaceOrder = {
            viewModel.onTapPlaceHolder()
            showPaymentMethodAndDeliveryAddressDialog = true
        },
        cartState = cartState,
        showErrorDialog = showErrorDialog,
        onTapOKButtonDialog = {
            showErrorDialog = false
        },
        showPaymentMethodAndDeliveryAddressDialog = showPaymentMethodAndDeliveryAddressDialog,
        onDismissPaymentAndDeliveryAddressDialog = {
            showPaymentMethodAndDeliveryAddressDialog = false
        },
        onTapSelectDeliveryAddressAndPaymentMethod = { paymentMethodId, deliveryAddressId ->
            showPaymentMethodAndDeliveryAddressDialog = false
            onPlaceOrder()
        },
        onTapNewCreateInformation = {
            showPaymentMethodAndDeliveryAddressDialog = false
            onTapNewCreateInformation()
        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {},
    cartState: CartState,
    onTapOKButtonDialog: () -> Unit,
    onTapSelectDeliveryAddressAndPaymentMethod: (Long, Long) -> Unit,
    onDismissPaymentAndDeliveryAddressDialog: () -> Unit,
    showErrorDialog: Boolean,
    showPaymentMethodAndDeliveryAddressDialog: Boolean,
    onTapNewCreateInformation: () -> Unit = {},
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
                ), CartItemVM(
                    id = 2,
                    title = "Fries",
                    price = 3.99,
                    image = "https://media.istockphoto.com/id/603906484/photo/vegetable-salad.jpg?s=612x612&w=0&k=20&c=f7BnJRCqLKaj_DEQB1SB71_eRT8y1XRP52dDyYRSxuE=",
                    qty = 1
                ), CartItemVM(
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
            CenterAlignedTopAppBar(title = { Text(CART_TITLE) }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            })
        }) { inner ->
        Column(
            modifier = Modifier.fillMaxSize().padding(inner)
        ) {
            if (cartState.uiState == UiState.LOADING) {
                AppLoadingDialog()
            }

            Column(Modifier.fillMaxWidth()) {
                cart.forEach { item ->
                    CartRow(item = item, onMinus = {
                        cart = cart.map {
                            if (it.id == item.id) it.copy(qty = (it.qty - 1).coerceAtLeast(0)) else it
                        }
                    }, onPlus = {
                        cart = cart.map {
                            if (it.id == item.id) it.copy(qty = it.qty + 1) else it
                        }
                    })
                }
            }

            HorizontalDivider(
                Modifier.padding(vertical = MARGIN_MEDIUM).padding(MARGIN_CARD_MEDIUM_2)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
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

            AppPrimaryButton(
                onClick = onPlaceOrder,
                modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_2),
                text = PLACE_ORDER_TITLE,
            )

            Spacer(Modifier.height(MARGIN_MEDIUM_2))

            if (showErrorDialog) {
                AppDialog(
                    title = DELIVERY_ADDRESS_AND_PAYMENT_METHOD_ERROR_TITLE,
                    message = cartState.errorMessage,
                    confirmText = OK_TEXT,
                    onConfirm = { onTapOKButtonDialog() },
                    onDismissRequest = { onTapOKButtonDialog() },
                )
            }

            if (showPaymentMethodAndDeliveryAddressDialog) {
                val paymentAndAddress = cartState.paymentAndAddress

                if (paymentAndAddress != null) {
                    PaymentAndAddressDialog(
                        onTapNewCreateInformation = onTapNewCreateInformation,
                        data = paymentAndAddress,
                        onDismissRequest = onDismissPaymentAndDeliveryAddressDialog,
                        onConfirm = { paymentMethodId, deliveryAddressId ->
                            onDismissPaymentAndDeliveryAddressDialog()
                            onTapSelectDeliveryAddressAndPaymentMethod(
                                paymentMethodId, deliveryAddressId
                            )
                        })
                } else {
                    AppDialog(
                        title = DELIVERY_ADDRESS_AND_PAYMENT_METHOD_ERROR_TITLE,
                        message = "No saved payment methods or delivery addresses found.",
                        confirmText = OK_TEXT,
                        onConfirm = { onDismissPaymentAndDeliveryAddressDialog() },
                        onDismissRequest = { onDismissPaymentAndDeliveryAddressDialog() },
                    )
                }
            }
        }
    }
}


@Composable
private fun CartRow(
    item: CartItemVM,
    onMinus: () -> Unit,
    onPlus: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = MARGIN_MEDIUM_2, vertical = MARGIN_CARD_MEDIUM_2),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AppNetworkImage(
            imageUrl = item.image,
            modifier = Modifier.size(CART_IMAGE_SIZE),
            shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2)
        )

        Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))

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

        Text(
            text = "$${item.price}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun StepperButton(
    text: String, onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(MARGIN_LARGE)
            .border(1.5.dp, MaterialTheme.colorScheme.error, CircleShape)
            .background(MaterialTheme.colorScheme.surface, CircleShape).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.SemiBold)
    }
}

data class CartItemVM(
    val id: Int, val title: String, val price: Double, val image: String, val qty: Int
)


@Composable
fun PaymentAndAddressDialog(
    data: PaymentAndAddressVO,
    onDismissRequest: () -> Unit,
    onConfirm: (paymentMethodId: Long, deliveryAddressId: Long) -> Unit,
    onTapNewCreateInformation: () -> Unit,
) {
    var selectedAddressId by remember {
        mutableStateOf(data.getDeliveryAddressByFilterEmptyData().firstOrNull()?.id)
    }
    var selectedPaymentMethodId by remember {
        mutableStateOf(data.getPaymentMethodsByFilterEmptyData().firstOrNull()?.id)
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(MARGIN_MEDIUM_3),
        containerColor = Color.White,
        title = {
            Column {
                Text(
                    text = CHECK_OUT_INFORMATION_TEXT,
                    fontWeight = FontWeight.Bold,
                    fontSize = TEXT_REGULAR_3X,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(Modifier.height(MARGIN_SMALL))
                Text(
                    text = CHECK_OUT_INFORMATION_DESCRIPTION_TEXT,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    fontSize = TEXT_REGULAR,
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                SectionHeader(
                    icon = Icons.Outlined.LocationOn,
                    title = CARD_DELIVERY_ADDRESS_TEXT
                )

                Spacer(Modifier.height(MARGIN_CARD_MEDIUM_2))

                if (data.deliveryAddresses?.isEmpty()?:true) {
                    EmptyStateCard(
                        icon = Icons.Outlined.Home,
                        message = DELIVERY_ADDRESS_NO_SAVE_TEXT
                    )
                } else {
                    data.deliveryAddresses.forEachIndexed { index, address ->
                        AddressCard(
                            address = address,
                            isSelected = selectedAddressId == address.id,
                            onClick = { selectedAddressId = address.id }
                        )
                        if (index < data.deliveryAddresses.size - 1) {
                            Spacer(Modifier.height(MARGIN_MEDIUM))
                        }
                    }
                }

                Spacer(Modifier.height(MARGIN_LARGE))


                SectionHeader(
                    icon = Icons.Outlined.Create,
                    title = PAYMENT_METHODS_TITLE
                )

                Spacer(Modifier.height(MARGIN_CARD_MEDIUM_2))

                if (data.paymentMethods?.isEmpty()?:true) {
                    EmptyStateCard(
                        icon = Icons.Outlined.Info,
                        message = PAYMENT_METHOD_NO_SAVE_TEXT
                    )
                } else {
                    data.paymentMethods.forEachIndexed { index, pm ->
                        PaymentMethodCard(
                            paymentMethod = pm,
                            isSelected = selectedPaymentMethodId == pm.id,
                            onClick = { selectedPaymentMethodId = pm.id }
                        )
                        if (index < data.paymentMethods.size - 1) {
                            Spacer(Modifier.height(MARGIN_MEDIUM))
                        }
                    }
                }
            }
        },
        confirmButton = {
            AppPrimaryButton(
                modifier = Modifier.fillMaxWidth().height(DEFAULT_BUTTON_HEIGHT),
                onClick = {
                    val pmId = selectedPaymentMethodId
                    val addrId = selectedAddressId
                    if (pmId != null && addrId != null) {
                        onConfirm(pmId, addrId)
                    } else {
                        onDismissRequest()
                    }
                },
                enabled = selectedPaymentMethodId != null && selectedAddressId != null,
                shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
                text = CONTINUE_TEXT,
            )
        },
        dismissButton = {
            OutlinedButton(
                onClick = {
                    onDismissRequest()
                    onTapNewCreateInformation()
                },
                border = BorderStroke(1.5.dp, PRIMARY_COLOR),
                shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DEFAULT_BUTTON_HEIGHT)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = PRIMARY_COLOR,
                    modifier = Modifier.size(MARGIN_MEDIUM_3)
                )
                Spacer(Modifier.width(MARGIN_MEDIUM))
                Text(
                    text = ADD_NEW_INFORMATION,
                    color = PRIMARY_COLOR,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = TEXT_REGULAR_2X,
                )
            }
        }
    )
}

@Composable
private fun SectionHeader(icon: ImageVector, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            color = PRIMARY_COLOR.copy(alpha = 0.1f),
            shape = RoundedCornerShape(MARGIN_MEDIUM),
            modifier = Modifier.size(MARGIN_XLARGE)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = PRIMARY_COLOR,
                    modifier = Modifier.size(MARGIN_MEDIUM_3)
                )
            }
        }
        Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = TEXT_REGULAR_2X,
            color = Color(0xFF1A1A1A)
        )
    }
}

@Composable
private fun AddressCard(
    address: DeliveryAddressVO,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
        color = if (isSelected) PRIMARY_COLOR.copy(alpha = 0.08f) else Color.White,
        border = if (isSelected) BorderStroke(MARGIN_XSMALL, PRIMARY_COLOR) else null,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(MARGIN_MEDIUM_2)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = PRIMARY_COLOR,
                    unselectedColor = Color(0xFFCCCCCC)
                )
            )
            Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = address.streetAddress,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    color = if (isSelected) Color(0xFF1A1A1A) else Color.Black
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = PRIMARY_COLOR,
                    modifier = Modifier.size(MARGIN_LARGE)
                )
            }
        }
    }
}

@Composable
private fun PaymentMethodCard(
    paymentMethod: PaymentMethodVO,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val masked = "**** **** **** ${paymentMethod.cardNumber.takeLast(4)}"

    Surface(
        shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
        color = if (isSelected) PRIMARY_COLOR.copy(alpha = 0.08f) else Color.White,
        border = if (isSelected) BorderStroke(MARGIN_XSMALL, PRIMARY_COLOR) else null,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(MARGIN_MEDIUM_2)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = PRIMARY_COLOR,
                    unselectedColor = Color(0xFFCCCCCC)
                )
            )
            Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))

            // Card Icon
            Surface(
                color = Color(0xFF2C3E50),
                shape = RoundedCornerShape(MARGIN_SMALL),
                modifier = Modifier.size(MARGIN_40)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Outlined.Create,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(MARGIN_LARGE)
                    )
                }
            }

            Spacer(Modifier.width(MARGIN_CARD_MEDIUM_2))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = masked,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    fontSize = TEXT_REGULAR,
                    color = if (isSelected) Color(0xFF1A1A1A) else Color(0xFF333333),
                    letterSpacing = 0.5.sp
                )
                Spacer(Modifier.height(MARGIN_SMALL))
                Text(
                    text = "Expires ${paymentMethod.expiryDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF666666),
                    fontSize = TEXT_SMALL,
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = PRIMARY_COLOR,
                    modifier = Modifier.size(MARGIN_LARGE)
                )
            }
        }
    }
}

@Composable
private fun EmptyStateCard(icon: ImageVector, message: String) {
    Surface(
        shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
        color = Color(0xFFF9F9F9),
        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(MARGIN_LARGE)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(MARGIN_40)
            )
            Spacer(Modifier.height(MARGIN_CARD_MEDIUM_2))
            Text(
                text = message,
                textAlign = TextAlign.Center
            )
        }
    }
}

