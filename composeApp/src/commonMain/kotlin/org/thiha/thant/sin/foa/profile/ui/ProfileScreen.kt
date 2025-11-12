package org.thiha.thant.sin.foa.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.ABOUT_TITLE
import org.thiha.thant.sin.foa.core.CHANGE_PASSWORD_TITLE
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
import org.thiha.thant.sin.foa.core.EDIT_PROFILE_TITLE
import org.thiha.thant.sin.foa.core.LOGOUT_TITLE
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.MARGIN_XXLARGE
import org.thiha.thant.sin.foa.core.NOTIFICATION_TITLE
import org.thiha.thant.sin.foa.core.PAYMENT_METHODS_TITLE
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.SETTINGS_TITLE
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onTapAboutScreen: () -> Unit, onTapLogout: () -> Unit) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text("Profile", fontWeight = FontWeight.W600)
            })
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
                .padding(horizontal = MARGIN_CARD_MEDIUM_2)
        ) {
            Text("Thiha Thant Sin", fontSize = TEXT_LARGE_3x, fontWeight = FontWeight.W700)
            Text(
                " thantsin7755@gmail.com",
                fontSize = TEXT_REGULAR_2X,
                fontWeight = FontWeight.W400,
                color = SECONDARY_COLOR
            )
            Spacer(
                modifier = Modifier.height(MARGIN_MEDIUM_3)
            )
            ProfileItem(EDIT_PROFILE_TITLE) {}
            ProfileItem(CHANGE_PASSWORD_TITLE) {}
            ProfileItem(PAYMENT_METHODS_TITLE) {}
            ProfileItem(NOTIFICATION_TITLE) {}
            ProfileItem(SETTINGS_TITLE) {}
            ProfileItem(ABOUT_TITLE) {
                onTapAboutScreen()
            }
            Spacer(
                modifier = Modifier.height(MARGIN_XXLARGE)
            )
            AppPrimaryButton(
                textColor = Color.Black,
                containerColor = Color.Black.copy(alpha = 0.1F),
                modifier = Modifier.fillMaxWidth(),
                text = LOGOUT_TITLE,
                onClick = {
                    onTapLogout()
                },
            )
        }
    }
}


@Composable
fun ProfileItem(label: String, onTap: () -> Unit) {
    Row(modifier = Modifier.clickable {
        onTap()
    }.padding(MARGIN_CARD_MEDIUM_2)) {
        Text(
            label,
            fontSize = TEXT_REGULAR_2X,
            fontWeight = FontWeight.W400,
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painterResource(Res.drawable.chevron_right_icon),
            contentDescription = null,
            modifier = Modifier.padding(end = MARGIN_CARD_MEDIUM_2).size(
                DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
            )
        )
    }
}