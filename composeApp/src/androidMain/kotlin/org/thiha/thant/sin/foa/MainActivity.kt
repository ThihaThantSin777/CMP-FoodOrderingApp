package org.thiha.thant.sin.foa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.thiha.thant.sin.foa.core.persistence.getDatabaseBuilderAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val databaseBuilder = getDatabaseBuilderAndroid(this)
        setContent {
            App(databaseBuilder)
        }
    }
}

