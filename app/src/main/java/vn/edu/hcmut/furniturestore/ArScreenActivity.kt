package vn.edu.hcmut.furniturestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import vn.edu.hcmut.furniturestore.adapter.screens.ARScreen
import vn.edu.hcmut.furniturestore.adapter.theme.AppTheme

class ARScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ARScreen()
            }
        }
    }
}