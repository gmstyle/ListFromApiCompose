package it.icsone.listfromapicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import it.icsone.listfromapicompose.router.AppNavigationGraph
import it.icsone.listfromapicompose.ui.theme.ListFromApiComposeTheme

class MainActivity : ComponentActivity() {

    private lateinit var myApplication: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myApplication = application as MyApplication

        setContent {
            val navController = rememberNavController()
            ListFromApiComposeTheme {
                AppNavigationGraph(navHostController = navController)
            }
        }
    }
}

