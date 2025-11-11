package org.thiha.thant.sin.foa.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import org.thiha.thant.sin.foa.core.DEFAULT_BUTTON_HEIGHT
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = DEFAULT_BUTTON_HEIGHT,
    shape: Shape = RoundedCornerShape(MARGIN_CARD_MEDIUM_2),
    containerColor: Color = PRIMARY_COLOR,
    contentColor: Color = Color.White,
    textStyle: TextStyle = TextStyle(fontSize = TEXT_REGULAR_2X)
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        Text(text, color = contentColor, style = textStyle)
    }
}