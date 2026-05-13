package com.spendwise.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spendwise.app.presentation.add_edit_transaction.AddEditTransactionScreen
import com.spendwise.app.presentation.transactions.TransactionListScreen
import com.spendwise.app.presentation.util.Screen
import com.spendwise.app.ui.theme.SpendWiseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendWiseTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TransactionListScreen.route
                    ) {
                        composable(route = Screen.TransactionListScreen.route) {
                            TransactionListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditTransactionScreen.route +
                                    "?transactionId={transactionId}",
                            arguments = listOf(
                                navArgument(
                                    name = "transactionId"
                                ) {
                                    type = NavType.LongType
                                    defaultValue = -1L
                                }
                            )
                        ) {
                            AddEditTransactionScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
