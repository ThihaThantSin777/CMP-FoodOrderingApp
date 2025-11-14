package org.thiha.thant.sin.foa.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.thiha.thant.sin.foa.core.PRIMARY_COLOR

@Composable
fun AppDialog(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    dismissText: String? = null,
    onDismiss: (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismissRequest()
                }
            ) {
                Text(text = confirmText, color = PRIMARY_COLOR)
            }
        },
        dismissButton = if (dismissText != null && onDismiss != null) {
            {
                TextButton(
                    onClick = {
                        onDismiss()
                        onDismissRequest()
                    }
                ) {
                    Text(text = dismissText, color = Color.Black)
                }
            }
        } else {
            null
        }
    )
}
