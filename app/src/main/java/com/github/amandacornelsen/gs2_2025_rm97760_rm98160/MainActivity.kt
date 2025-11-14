package com.github.amandacornelsen.gs2_2025_rm97760_rm98160

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.amandacornelsen.gs2_2025_rm97760_rm98160.screens.CalculoIMCScreen
import com.github.amandacornelsen.gs2_2025_rm97760_rm98160.screens.EquipeScreen
import com.github.amandacornelsen.gs2_2025_rm97760_rm98160.screens.LoginScreen
import com.github.amandacornelsen.gs2_2025_rm97760_rm98160.screens.MenuScreen
import com.github.amandacornelsen.gs2_2025_rm97760_rm98160.ui.theme.GS2_2025_RM97760_RM98160Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GS2_2025_RM97760_RM98160Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                    ) {
                        composable(route = "login") {
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "menu") {
                            MenuScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable(route = "equipe") {
                            EquipeScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }
                        composable(route = "calculoIMC") {
                            CalculoIMCScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                            )
                        }

                        }
                    }
                }
            }
        }
    }