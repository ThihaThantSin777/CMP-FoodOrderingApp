package org.thiha.thant.sin.foa.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.thiha.thant.sin.foa.components.AppOutlinedTextField
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.DONT_HAVE_ACCOUNT_TEXT
import org.thiha.thant.sin.foa.core.EMAIL_HINT_TEXT
import org.thiha.thant.sin.foa.core.FORGET_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.LOGIN_TEXT
import org.thiha.thant.sin.foa.core.MARGIN_40
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.PASSWORD_HINT_TEXT
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.SIGNUP_TEXT
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.WELCOME_BACK_TITLE_TEXT
import org.thiha.thant.sin.foa.core.utils.ValidatorUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onTapSignUp: () -> Unit, onTapForgetPassword: () -> Unit, onTapLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailErrorText by remember { mutableStateOf<String?>(null) }
    var passwordErrorText by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                actions = {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = null,
                        modifier = Modifier.padding(end = MARGIN_MEDIUM)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().background(Color.White)
                .padding(paddingValues = paddingValues)
        ) {
            Text(WELCOME_BACK_TITLE_TEXT, fontWeight = FontWeight.W700, fontSize = TEXT_LARGE_3x)

            Spacer(modifier = Modifier.height(MARGIN_LARGE))


            AppOutlinedTextField(
                value = email,
                isError = emailErrorText?.isNotEmpty() ?: false,
                onValueChange = { email = it },
                hint = EMAIL_HINT_TEXT,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3),
                errorText = emailErrorText
            )
            Spacer(modifier = Modifier.height(MARGIN_CARD_MEDIUM_2))
            AppOutlinedTextField(
                value = password,
                isError = passwordErrorText?.isNotEmpty() ?: false,
                onValueChange = { password = it },
                hint = PASSWORD_HINT_TEXT,
                isPassword = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3),
                errorText = passwordErrorText
            )
            Spacer(modifier = Modifier.height(MARGIN_CARD_MEDIUM_2))
            Text(
                FORGET_PASSWORD_TEXT,
                color = SECONDARY_COLOR,
                modifier = Modifier.align(Alignment.End).padding(horizontal = MARGIN_MEDIUM_3)
                    .clickable {
                        onTapForgetPassword()
                    }
            )
            Spacer(modifier = Modifier.height(MARGIN_CARD_MEDIUM_2))
            AppPrimaryButton(
                text = LOGIN_TEXT,
                onClick = {
                    emailErrorText = ValidatorUtils.checkEmailValidation(email);
                    passwordErrorText = ValidatorUtils.checkPasswordValidation(password);

                    if ((emailErrorText?.isEmpty() ?: false) && (passwordErrorText?.isEmpty()
                            ?: false)
                    ) {
                        onTapLogin()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3)
            )

            Spacer(modifier = Modifier.height(MARGIN_40))

            Text(DONT_HAVE_ACCOUNT_TEXT, color = Color.Black, fontSize = TEXT_REGULAR_2X)
            Spacer(modifier = Modifier.height(MARGIN_MEDIUM))
            Text(
                SIGNUP_TEXT,
                color = SECONDARY_COLOR,
                fontSize = TEXT_REGULAR_2X,
                fontWeight = FontWeight.W700,
                modifier = Modifier.clickable {
                    onTapSignUp()
                }
            )
        }
    }
}