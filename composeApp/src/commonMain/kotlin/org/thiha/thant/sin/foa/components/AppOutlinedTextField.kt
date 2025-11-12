package org.thiha.thant.sin.foa.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_FIELD_BG_COLOR

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email,
    shape: Shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        unfocusedContainerColor = TEXT_FIELD_BG_COLOR,
        focusedContainerColor = TEXT_FIELD_BG_COLOR,
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        singleLine = singleLine,
        placeholder = { Text(hint, color = SECONDARY_COLOR) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = shape,
        isError = isError,
        colors = colors,
        visualTransformation = when {
            !isPassword -> VisualTransformation.None
            else -> PasswordVisualTransformation()
        },
        leadingIcon = leadingIcon,
        supportingText = {
            if (isError && !errorText.isNullOrBlank()) {
                Text(errorText, color = Color.Red)
            }
        }
    )
}

