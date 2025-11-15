package org.thiha.thant.sin.foa.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBack: () -> Unit = {}
) {
    val labelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
    val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10f)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("About") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { inner ->
        LazyColumn  (
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            item {
                SectionTitle("Our Mission")
                Spacer(Modifier.height(8.dp))
                Text(
                    "At FlavorDash, we're dedicated to connecting food lovers with their favorite local restaurants. " +
                            "Our mission is to make ordering food online simple, fast, and reliable, while supporting the growth of local businesses.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            item { Spacer(Modifier.height(28.dp)) }


            item {
                SectionTitle("Company Information")
                Spacer(Modifier.height(16.dp))

                Row  (Modifier.fillMaxWidth()) {
                    Column (
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        HorizontalDivider(color = dividerColor)
                        Spacer(Modifier.height(12.dp))
                        Label("Company Name", labelColor)
                        Spacer(Modifier.height(6.dp))
                        Text("FlavorDash Inc.", style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(Modifier.width(20.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        HorizontalDivider(color = dividerColor)
                        Spacer(Modifier.height(12.dp))
                        Label("Founded", labelColor)
                        Spacer(Modifier.height(6.dp))
                        Text("2018", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(Modifier.height(22.dp))

                Column {
                    HorizontalDivider(color = dividerColor)
                    Spacer(Modifier.height(12.dp))
                    Label("Headquarters", labelColor)
                    Spacer(Modifier.height(6.dp))
                    Text("San Francisco, CA", style = MaterialTheme.typography.bodyMedium)
                }
            }

            item { Spacer(Modifier.height(28.dp)) }


            item {
                SectionTitle("Contact Us")
                Spacer(Modifier.height(16.dp))

                ContactItem(
                    title = "Customer Support",
                    value = "support@flavordash.com",
                    dividerColor = dividerColor,
                    labelColor = labelColor
                )

                Spacer(Modifier.height(12.dp))

                ContactItem(
                    title = "Business Inquiries",
                    value = "business@flavordash.com",
                    dividerColor = dividerColor,
                    labelColor = labelColor
                )
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold)
    )
}

@Composable
private fun Label(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = color
    )
}

@Composable
private fun ContactItem(
    title: String,
    value: String,
    dividerColor: Color,
    labelColor: Color
) {
    Column(Modifier.fillMaxWidth()) {
        Divider(color = dividerColor)
        Spacer(Modifier.height(12.dp))
        Label(title, labelColor)
        Spacer(Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(value, style = MaterialTheme.typography.bodyMedium)
        }
    }
}