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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.thiha.thant.sin.foa.auth.state.AuthState
import org.thiha.thant.sin.foa.auth.viewmodel.ForgetPasswordViewModel
import org.thiha.thant.sin.foa.components.AppDialog
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppOutlinedTextField
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.CHECK_EMAIL_ERROR_TITLE
import org.thiha.thant.sin.foa.core.CONTINUE_TEXT
import org.thiha.thant.sin.foa.core.EMAIL_HINT_TEXT
import org.thiha.thant.sin.foa.core.FORGET_PASSWORD_SUB_TEXT
import org.thiha.thant.sin.foa.core.FORGET_PASSWORD_TITLE_TEXT
import org.thiha.thant.sin.foa.core.MARGIN_LARGE
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.OK_TEXT
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.utils.ValidatorUtils
import org.thiha.thant.sin.foa.core.utils.enums.UiState

@Composable
fun ForgetPasswordRoute(
    viewModel: ForgetPasswordViewModel,
    onTapContinue: (email: String) -> Unit,
    onTapBack: () -> Unit
) {
    val authState by viewModel.state.collectAsStateWithLifecycle()
    var showErrorDialog by remember { mutableStateOf(false) }
    var saveEmail: String = "";

    LaunchedEffect(authState.uiState) {
        if (authState.uiState == UiState.SUCCESS) {
            onTapContinue(saveEmail);
        }

        if (authState.uiState == UiState.FAIL) {
            showErrorDialog = true
        }
    }

    ForgetPasswordScreen(
        onTapOKButtonDialog = {
            showErrorDialog = false;
        },
        onTapBack = onTapBack,
        showErrorDialog = showErrorDialog,
        state = authState,
        onTapContinue = { email ->
            saveEmail = email
            viewModel.onTapForgetPasswordCheck(email)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetPasswordScreen(
    onTapOKButtonDialog: () -> Unit,
    onTapContinue: (email: String) -> Unit,
    onTapBack: () -> Unit,
    state: AuthState,
    showErrorDialog: Boolean,
) {
    var email by remember { mutableStateOf("") }
    var emailErrorText by remember { mutableStateOf<String?>(null) }

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
        if (state.uiState == UiState.LOADING) {
            AppLoadingDialog()
        }

        Column(
            modifier = Modifier.fillMaxSize().background(Color.White)
                .padding(paddingValues = paddingValues)
        ) {
            Text(
                FORGET_PASSWORD_TITLE_TEXT,
                fontWeight = FontWeight.W700,
                fontSize = TEXT_LARGE_3x,
                modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_3),
            )
            Spacer(modifier = Modifier.height(MARGIN_LARGE))
            Text(
                FORGET_PASSWORD_SUB_TEXT, fontSize = TEXT_REGULAR_2X,
                modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_3),
            )

            Spacer(modifier = Modifier.height(MARGIN_LARGE))

            AppOutlinedTextField(
                value = email,
                isError = emailErrorText?.isNotEmpty() ?: false,
                onValueChange = { email = it },
                hint = EMAIL_HINT_TEXT,
                modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_3),
                errorText = emailErrorText
            )

            Spacer(modifier = Modifier.weight(1F))
            AppPrimaryButton(
                text = CONTINUE_TEXT, onClick = {
                    emailErrorText = ValidatorUtils.checkEmailValidation(email);

                    if ((emailErrorText?.isEmpty() ?: false)) {
                        onTapContinue(email)
                    }
                }, modifier = Modifier.fillMaxWidth().padding(horizontal = MARGIN_MEDIUM_3)
            )

            Spacer(modifier = Modifier.height(MARGIN_MEDIUM))

            if (showErrorDialog) {
                AppDialog(
                    title = CHECK_EMAIL_ERROR_TITLE,
                    message = state.errorMessage ?: "",
                    confirmText = OK_TEXT,
                    onConfirm = {
                        onTapOKButtonDialog()
                    },
                    onDismissRequest = {
                        onTapOKButtonDialog()
                    },
                )
            }

        }

    }
}