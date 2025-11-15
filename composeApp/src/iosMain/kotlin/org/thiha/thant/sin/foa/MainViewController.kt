package org.thiha.thant.sin.foa

import androidx.compose.ui.window.ComposeUIViewController
import org.thiha.thant.sin.foa.persistence.getDatabaseBuilderIOS

fun MainViewController() = ComposeUIViewController {
    val databaseBuilder = getDatabaseBuilderIOS()
    App(databaseBuilder)
}