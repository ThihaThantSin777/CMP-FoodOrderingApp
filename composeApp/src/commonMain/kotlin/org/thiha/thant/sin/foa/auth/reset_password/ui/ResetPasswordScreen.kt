package org.thiha.thant.sin.foa.auth.reset_password.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.thiha.thant.sin.foa.components.AppOutlinedTextField
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.CONFIRM_PASSWORD_HINT_TEXT
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.PASSWORD_HINT_TEXT
import org.thiha.thant.sin.foa.core.RESET_PASSWORD_TEXT
import org.thiha.thant.sin.foa.core.RESET_PASSWORD_TITLE_TEXT
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.utils.ValidatorUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(onTapBack: () -> Unit, onTapResetPassword: () -> Unit) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf<String?>(null) }
    var confirmPasswordErrorText by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onTapBack()
                        })
                },
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White)
                .padding(paddingValues = paddingValues)
        ) {
            Text(
                RESET_PASSWORD_TITLE_TEXT,
                fontWeight = FontWeight.W700,
                fontSize = TEXT_LARGE_3x,
                modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_3),
            )
            Spacer(modifier = Modifier.height(MARGIN_LARGE))

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

            AppOutlinedTextField(
                value = confirmPassword,
                isError = confirmPasswordErrorText?.isNotEmpty() ?: false,
                onValueChange = { confirmPassword = it },
                hint = CONFIRM_PASSWORD_HINT_TEXT,
                isPassword = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MARGIN_MEDIUM_3),
                errorText = confirmPasswordErrorText
            )
            Spacer(modifier = Modifier.weight(1F))
            AppPrimaryButton(
                text = RESET_PASSWORD_TEXT, onClick = {
                    passwordErrorText = ValidatorUtils.checkPasswordValidation(password)
                    confirmPasswordErrorText =
                        ValidatorUtils.checkConfirmPasswordValidation(password, confirmPassword);

                    if ((passwordErrorText?.isEmpty()
                            ?: false) && (confirmPasswordErrorText?.isEmpty() ?: false)
                    ) {
                        onTapResetPassword()
                    }
                }, modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_3)
            )

            Spacer(modifier = Modifier.height(MARGIN_MEDIUM))

        }
    }
}