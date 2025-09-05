package gupta.abhishek.adgamaassessmentapp.ui.navigation

import BookDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.firebase.auth.FirebaseAuth

import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeScreen
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.ui.auth.AuthScreen
import gupta.abhishek.adgamaassessmentapp.ui.auth.AuthViewModel
import gupta.abhishek.adgamaassessmentapp.ui.auth.LogInScreen
import gupta.abhishek.adgamaassessmentapp.ui.auth.SignUpScreen


@Composable
fun Navigation(
    modifier: Modifier,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,

    ) {


    val auth = FirebaseAuth.getInstance()

    var isUserLoggedIn by remember { mutableStateOf(auth.currentUser != null) }

    LaunchedEffect(Unit) {
        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            isUserLoggedIn = auth.currentUser != null
        }
    }
    val startDestination = if (isUserLoggedIn) {
        Routes.HomeScreen
    } else {
        Routes.AuthScreen
    }

    val navController: NavHostController = rememberNavController()
    GlobalNavControllerHolder.navController = navController



    NavHost(navController = navController, startDestination = startDestination) {
        composable<Routes.AuthScreen> {

            AuthScreen(authViewModel = authViewModel, navController = navController)
        }
        composable<Routes.SignIn> {

            SignUpScreen(authViewModel = authViewModel, navController = navController)
        }

        composable<Routes.LogInScreen> {

            LogInScreen(authViewModel = authViewModel, navController = navController)
        }

        composable<Routes.HomeScreen> {

            HomeScreen(

                authViewModel = authViewModel,
                homeViewmodel = homeViewModel,
                navController = navController
            )
        }

        composable<Routes.DetailScreen> {
            val args = it.toRoute<Routes.DetailScreen>()
            BookDetailScreen(
                title = args.title,
                author = args.author,
                coverUrl = args.coverUrl,
                description = args.description,
                publishedYear = args.publishedYear,
                genre = args.genre, homeViewModel = homeViewModel
            )


        }
    }

}

object GlobalNavControllerHolder {
    lateinit var navController: NavHostController
}