package com.visionary_ventures.inventoryvision.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.visionary_ventures.inventoryvision.ui.theme.screens.home.Home_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.kpi.KPICARD_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.kpi.KPIOVERVIEW_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.kpi.KPIPAGE_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.layout.LayoutPage_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.layout.Layout_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.loggin.Loggin_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.products.ProductForm_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.products.ProductPage_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.products.ProductTable_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.profile.ProfilePage_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.register.Register_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.settings.SettingsPage_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.splash.Splash_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.tools.InventoryTracker_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.tools.ReportGenerator_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.tools.ToolsPage_Screen
import com.visionary_ventures.inventoryvision.ui.theme.screens.wallet.WalletPage_Screen


@Composable
fun App_Nav_Host(modifier: Modifier = Modifier,
                 navController: NavHostController= rememberNavController(),
                 startDestination:String= ROUTE_SPLASH){

    NavHost(navController = navController,
        modifier = modifier,
        startDestination = startDestination) {


        composable(ROUTE_SPLASH) {
            Splash_Screen(navController)
        }

        composable(ROUTE_HOME) {
            Home_Screen(navController)
        }

        composable(ROUTE_LOGGIN) {
            Loggin_Screen(navController)
        }

        composable(ROUTE_REGISTER) {
            Register_Screen(navController)
        }

        composable(ROUTE_KPICARD) {
            KPICARD_Screen(navController)
        }

        composable(ROUTE_KPIPAGE) {
            KPIPAGE_Screen(navController)
        }

        composable(ROUTE_KPIOVERVIEW) {
            KPIOVERVIEW_Screen(navController)
        }

        composable(ROUTE_LAYOUTSCREEN) {
            Layout_Screen(navController)
        }

        composable(ROUTE_LAYOUTPAGE) {
            LayoutPage_Screen(navController)
        }

        composable(ROUTE_PRODUCTFORM) {
            ProductForm_Screen(navController)
        }

        composable(ROUTE_PRODUCTPAGE) {
            ProductPage_Screen(navController)
        }

        composable(ROUTE_PRODUCTTABLE) {
            ProductTable_Screen(navController)
        }

        composable(ROUTE_PROFILE) {
            ProfilePage_Screen(navController)
        }

        composable(ROUTE_SETTINGS) {
            SettingsPage_Screen(navController)
        }

        composable(ROUTE_TRACKER) {
            InventoryTracker_Screen(navController)
        }

        composable(ROUTE_GENERATOR) {
            ReportGenerator_Screen(navController)
        }

        composable(ROUTE_TOOLS) {
            ToolsPage_Screen(navController)
        }

        composable(ROUTE_WALLET) {
            WalletPage_Screen(navController)
        }

    }
}

