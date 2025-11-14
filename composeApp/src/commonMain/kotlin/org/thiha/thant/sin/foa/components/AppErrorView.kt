package org.thiha.thant.sin.foa.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.RETRY_TEXT
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X

@Composable
fun AppErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onTapRetry: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(MARGIN_MEDIUM_3)
    ) {
        Text(
            text = message,
            color = Color.Red,
            fontWeight = FontWeight.W600,
            fontSize = TEXT_REGULAR_2X,
            textAlign = TextAlign.Center,
        )

        if (onTapRetry != null) {
            Text(
                RETRY_TEXT, color = Color.Red,
                fontWeight = FontWeight.W400,
                fontSize = TEXT_REGULAR_2X,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    onTapRetry()
                }
            )
        }


    }
}