package org.thiha.thant.sin.foa.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import org.thiha.thant.sin.foa.core.*
import org.thiha.thant.sin.foa.components.AppOutlinedTextField
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.utils.ValidatorUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    onBack: () -> Unit = {},
    onPlaceOrder: () -> Unit = {}
) {
    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }     // MM/YY
    var cvv by remember { mutableStateOf("") }
    var nameOnCard by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var saveForFuture by remember { mutableStateOf(false) }

    // error messages (empty = no error)
    var cardErr by remember { mutableStateOf("") }
    var expErr by remember { mutableStateOf("") }
    var cvvErr by remember { mutableStateOf("") }
    var nameErr by remember { mutableStateOf("") }
    var addrErr by remember { mutableStateOf("") }

    fun validateAll() {
        cardErr = ValidatorUtils.checkCardNumberValidation(cardNumber)
        expErr  = ValidatorUtils.checkExpiryValidation(expiry)
        cvvErr  = ValidatorUtils.checkCvvValidation(cvv)
        nameErr = ValidatorUtils.checkNameOnCardValidation(nameOnCard)
        addrErr = ValidatorUtils.checkAddressValidation(address)
    }

    fun allValid() =
        cardErr.isEmpty() && expErr.isEmpty() && cvvErr.isEmpty() && nameErr.isEmpty() && addrErr.isEmpty()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(CHECK_OUT_TITLE) },
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
                    text = PLACE_ORDER_TITLE,
                    onClick = {
                        validateAll()
                        if (allValid()) onPlaceOrder()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(DEFAULT_BUTTON_HEIGHT)
                )
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = MARGIN_MEDIUM_3)
                .verticalScroll(rememberScrollState())
        ) {
            // Payment Details
            Text(
                PAYMENT_DETAILS_TITLE,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = MARGIN_MEDIUM_2, bottom = MARGIN_MEDIUM)
            )

            AppOutlinedTextField(
                value = cardNumber,
                onValueChange = {
                    cardNumber = it
                    if (cardErr.isNotEmpty()) cardErr = ""
                },
                hint = CARD_NUMBER_HINT_TEXT,
                keyboardType = KeyboardType.Number,
                isError = cardErr.isNotEmpty(),
                errorText = cardErr,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(MARGIN_MEDIUM_2))

            Row(Modifier.fillMaxWidth()) {
                AppOutlinedTextField(
                    value = expiry,
                    onValueChange = {
                        expiry = it
                        if (expErr.isNotEmpty()) expErr = ""
                    },
                    hint = CARD_EXPIRY_HINT_TEXT,
                    keyboardType = KeyboardType.Number,
                    isError = expErr.isNotEmpty(),
                    errorText = expErr,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(MARGIN_MEDIUM_3))
                AppOutlinedTextField(
                    value = cvv,
                    onValueChange = {
                        cvv = it
                        if (cvvErr.isNotEmpty()) cvvErr = ""
                    },
                    hint = CARD_CVC_HINT_TEXT,
                    keyboardType = KeyboardType.Number,
                    isError = cvvErr.isNotEmpty(),
                    errorText = cvvErr,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(MARGIN_MEDIUM_2))

            AppOutlinedTextField(
                value = nameOnCard,
                onValueChange = {
                    nameOnCard = it
                    if (nameErr.isNotEmpty()) nameErr = ""
                },
                hint = CARD_NAME_ON_CARD_HINT_TEXT,
                keyboardType = KeyboardType.Text,
                isError = nameErr.isNotEmpty(),
                errorText = nameErr,
                modifier = Modifier.fillMaxWidth()
            )

            // Delivery Address
            Spacer(Modifier.height(MARGIN_MEDIUM_3))
            Text(
                CARD_DELIVERY_ADDRESS_TEXT,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = MARGIN_MEDIUM)
            )

            AppOutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    if (addrErr.isNotEmpty()) addrErr = ""
                },
                hint = CARD_DELIVERY_ADDRESS_HINT_TEXT,
                keyboardType = KeyboardType.Text,
                singleLine = false,
                isError = addrErr.isNotEmpty(),
                errorText = addrErr,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(CHECK_OUT_TEXT_AREA_HEIGHT)
            )

            Spacer(Modifier.height(MARGIN_MEDIUM_2))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MARGIN_MEDIUM),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(SAVE_CAR_TEXT)
                Switch(
                    checked = saveForFuture,
                    onCheckedChange = { saveForFuture = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = PRIMARY_COLOR,
                        checkedTrackColor = SECONDARY_COLOR
                    )
                )
            }

            Spacer(Modifier.height(MARGIN_40))
        }
    }
}

