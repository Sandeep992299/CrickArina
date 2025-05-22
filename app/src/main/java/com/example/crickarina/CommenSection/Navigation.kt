package com.example.crickarina.CommenSection

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.crickarina.Model.NavIterm
import com.example.crickarina.Screen.*

@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()

    // Get the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute !in listOf("signin", "signup")) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavigationGraph(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItems = listOf(
        NavIterm("Home", Icons.Default.Home, "home"),
        NavIterm("Category", Icons.Default.List, "category"),
        NavIterm("Cart", Icons.Default.ShoppingCart, "cart"),
        NavIterm("Profile", Icons.Default.Person, "profile")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "signin",
        modifier = modifier
    ) {
        composable("signin") { SignIn(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable(
            "home",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) }
        ) {
            MainScreen(navController)
        }
        composable(
            "category",
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            ProductScreen(navController)
        }
        composable(
            "cart",
            enterTransition = { slideInVertically(initialOffsetY = { 1000 }) },
            exitTransition = { slideOutVertically(targetOffsetY = { -1000 }) }
        ) {
            CartPage()
        }
        composable(
            "profile",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) }
        ) {
            ProfilePage(navController)
        }
    }
}
