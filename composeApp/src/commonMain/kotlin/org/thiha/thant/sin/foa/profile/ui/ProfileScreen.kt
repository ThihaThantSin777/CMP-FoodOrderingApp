package org.thiha.thant.sin.foa.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import foodorderingapp.composeapp.generated.resources.Res
import foodorderingapp.composeapp.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource
import org.thiha.thant.sin.foa.components.AppDialog
import org.thiha.thant.sin.foa.components.AppErrorView
import org.thiha.thant.sin.foa.components.AppLoadingDialog
import org.thiha.thant.sin.foa.components.AppPrimaryButton
import org.thiha.thant.sin.foa.core.ABOUT_TITLE
import org.thiha.thant.sin.foa.core.CANCEL_TEXT
import org.thiha.thant.sin.foa.core.CHANGE_PASSWORD_TITLE
import org.thiha.thant.sin.foa.core.DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE
import org.thiha.thant.sin.foa.core.EDIT_PROFILE_TITLE
import org.thiha.thant.sin.foa.core.LOGOUT_CONFIRM_MESSAGE
import org.thiha.thant.sin.foa.core.LOGOUT_CONFIRM_TITLE
import org.thiha.thant.sin.foa.core.LOGOUT_TITLE
import org.thiha.thant.sin.foa.core.MARGIN_CARD_MEDIUM_2
import org.thiha.thant.sin.foa.core.MARGIN_MEDIUM_3
import org.thiha.thant.sin.foa.core.MARGIN_XXLARGE
import org.thiha.thant.sin.foa.core.NOTIFICATION_TITLE
import org.thiha.thant.sin.foa.core.OK_TEXT
import org.thiha.thant.sin.foa.core.PAYMENT_METHODS_TITLE
import org.thiha.thant.sin.foa.core.PROFILE_LOGOUT_FAIL_TITLE
import org.thiha.thant.sin.foa.core.SECONDARY_COLOR
import org.thiha.thant.sin.foa.core.SETTINGS_TITLE
import org.thiha.thant.sin.foa.core.TEXT_LARGE_3x
import org.thiha.thant.sin.foa.core.TEXT_REGULAR_2X
import org.thiha.thant.sin.foa.core.YES_TEXT
import org.thiha.thant.sin.foa.core.utils.enums.UiState
import org.thiha.thant.sin.foa.profile.state.ProfileState
import org.thiha.thant.sin.foa.profile.viewmodel.ProfileViewModel


@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel,
    onTapAboutScreen: () -> Unit,
    onTapLogout: () -> Unit
) {
    val profileState by viewModel.state.collectAsStateWithLifecycle()

    var showErrorDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(profileState.uiState, profileState.auth) {
        if (profileState.uiState == UiState.SUCCESS &&
            profileState.auth == null &&
            !profileState.isErrorFromOnTapLogout
        ) {

            onTapLogout()
        }

        if (profileState.uiState == UiState.FAIL && profileState.isErrorFromOnTapLogout) {
            showErrorDialog = true
        }
    }

    ProfileScreen(
        state = profileState,
        onTapAboutScreen = onTapAboutScreen,
        onTapLogout = {
            showLogoutDialog = true
        },
        onTapRetry = {
            viewModel.loadProfileData()
        },
        onTapOKButtonDialog = {
            showErrorDialog = false
        },
        showErrorDialog = showErrorDialog,
        showLogoutDialog = showLogoutDialog,
        onDismissLogoutDialog = {
            showLogoutDialog = false
        },
        onConfirmLogout = {
            showLogoutDialog = false
            viewModel.onTapLogout()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileState,
    onTapAboutScreen: () -> Unit,
    onTapLogout: () -> Unit,
    onTapRetry: () -> Unit,
    onTapOKButtonDialog: () -> Unit,
    showErrorDialog: Boolean,
    showLogoutDialog: Boolean,
    onDismissLogoutDialog: () -> Unit,
    onConfirmLogout: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Profile", fontWeight = FontWeight.W600)
                }
            )
        }
    ) { paddingValues ->
        when (state.uiState) {
            UiState.LOADING -> {
                AppLoadingDialog()
            }

            UiState.FAIL -> {
                if (state.isErrorFromProfileFetch) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AppErrorView(
                            message = state.errorMessage,
                            onTapRetry = onTapRetry
                        )
                    }
                } else {
                    ProfileSession(
                        paddingValues = paddingValues,
                        onTapAboutScreen = onTapAboutScreen,
                        onTapLogout = onTapLogout,
                        onTapOKButtonDialog = onTapOKButtonDialog,
                        showErrorDialog = showErrorDialog,
                        showLogoutDialog = showLogoutDialog,
                        onDismissLogoutDialog = onDismissLogoutDialog,
                        onConfirmLogout = onConfirmLogout,
                        state = state
                    )
                }
            }

            else -> {
                ProfileSession(
                    paddingValues = paddingValues,
                    onTapAboutScreen = onTapAboutScreen,
                    onTapLogout = onTapLogout,
                    onTapOKButtonDialog = onTapOKButtonDialog,
                    showErrorDialog = showErrorDialog,
                    showLogoutDialog = showLogoutDialog,
                    onDismissLogoutDialog = onDismissLogoutDialog,
                    onConfirmLogout = onConfirmLogout,
                    state = state
                )
            }
        }
    }
}

@Composable
fun ProfileSession(
    paddingValues: PaddingValues,
    onTapAboutScreen: () -> Unit,
    onTapLogout: () -> Unit,
    onTapOKButtonDialog: () -> Unit,
    showErrorDialog: Boolean,
    showLogoutDialog: Boolean,
    onDismissLogoutDialog: () -> Unit,
    onConfirmLogout: () -> Unit,
    state: ProfileState,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(horizontal = MARGIN_CARD_MEDIUM_2)
    ) {
        Text(
            text = state.auth?.fullName ?: "",
            fontSize = TEXT_LARGE_3x,
            fontWeight = FontWeight.W700
        )
        Text(
            text = state.auth?.email ?: "",
            fontSize = TEXT_REGULAR_2X,
            fontWeight = FontWeight.W400,
            color = SECONDARY_COLOR
        )

        Spacer(modifier = Modifier.height(MARGIN_MEDIUM_3))

        ProfileItem(EDIT_PROFILE_TITLE) {}
        ProfileItem(CHANGE_PASSWORD_TITLE) {}
        ProfileItem(PAYMENT_METHODS_TITLE) {}
        ProfileItem(NOTIFICATION_TITLE) {}
        ProfileItem(SETTINGS_TITLE) {}
        ProfileItem(ABOUT_TITLE) {
            onTapAboutScreen()
        }

        Spacer(modifier = Modifier.height(MARGIN_XXLARGE))

        AppPrimaryButton(
            textColor = Color.Black,
            containerColor = Color.Black.copy(alpha = 0.1F),
            modifier = Modifier.fillMaxWidth(),
            text = LOGOUT_TITLE,
            onClick = {
                onTapLogout()
            },
        )

        if (showLogoutDialog) {
            LogoutConfirmationDialog(
                onConfirmLogout = onConfirmLogout,
                onDismiss = onDismissLogoutDialog
            )
        }

        if (showErrorDialog) {
            AppDialog(
                title = PROFILE_LOGOUT_FAIL_TITLE,
                message = state.errorMessage,
                confirmText = OK_TEXT,
                onConfirm = { onTapOKButtonDialog() },
                onDismissRequest = { onTapOKButtonDialog() },
            )
        }
    }
}

@Composable
fun ProfileItem(label: String, onTap: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onTap() }
            .padding(MARGIN_CARD_MEDIUM_2)
    ) {
        Text(
            text = label,
            fontSize = TEXT_REGULAR_2X,
            fontWeight = FontWeight.W400,
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(Res.drawable.chevron_right_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(end = MARGIN_CARD_MEDIUM_2)
                .size(DEFAULT_ORDER_CHEVRON_RIGHT_ICON_SIZE)
        )
    }
}


@Composable
fun LogoutConfirmationDialog(
    onConfirmLogout: () -> Unit,
    onDismiss: () -> Unit,
) {
    AppDialog(
        title = LOGOUT_CONFIRM_TITLE,
        message = LOGOUT_CONFIRM_MESSAGE,
        confirmText = YES_TEXT,
        onConfirm = { onConfirmLogout() },
        onDismissRequest = { onDismiss() },
        dismissText = CANCEL_TEXT,
        onDismiss = { onDismiss() }
    )
}