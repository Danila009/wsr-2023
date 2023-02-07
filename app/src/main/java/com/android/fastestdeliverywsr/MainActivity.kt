package com.android.fastestdeliverywsr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "start",
                builder = {
                    composable("start"){
                        StartScreen(
                            navController = navController
                        )
                    }

                    composable("welcom"){
                        WelcomScreen(navController = navController)
                    }

                    composable("auth"){
                        AuthScreen(navController = navController)
                    }

                    composable("main_screen"){
                        MainScreen()
                    }

                    composable("reg"){
                        RegScreen(navController = navController)
                    }
                }
            )
        }
    }
}