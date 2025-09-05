package gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavController
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.pages.FavoritesPage
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.pages.HomePage
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.pages.ProfilePage
import gupta.abhishek.adgamaassessmentapp.ui.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    homeViewmodel: HomeViewModel,
    navController: NavController,
) {

    val navItems = listOf(
        NavItems(
            "Home", filledIcon = Icons.Filled.Home, outlinedIcon = Icons.Outlined.Home
        ),

        NavItems(
            "Favorites",
            filledIcon = Icons.Filled.Favorite,
            outlinedIcon = Icons.Outlined.FavoriteBorder
        ),

        NavItems(
            "Profile",
            filledIcon = Icons.Filled.AccountCircle,
            outlinedIcon = Icons.Outlined.AccountCircle
        )
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),


        bottomBar = {
            NavigationBar(modifier = Modifier.height(65.dp)) {

                navItems.fastForEachIndexed() { index, navItem ->
                    val isSelected = selectedIndex == index
                    val iconColor = if (isSelected) Color.White else Color.Black

                    NavigationBarItem(
                        selected = false, onClick = {
                            selectedIndex = index
                        }, icon = {
                            Icon(
                                imageVector = if (isSelected) navItem.filledIcon else navItem.outlinedIcon,
                                contentDescription = null,

                                )
                        },
                        label = {
                            Text(
                                text = navItem.title,
                                modifier = Modifier.offset(y = (-4).dp),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        })
                }


            }
        }) { innerPadding ->

        ContentScreen(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            selectedIndex = selectedIndex,
            navController = navController,
            homeViewModel = homeViewmodel,
            authViewModel = authViewModel,
        )


    }


}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    homeViewModel: HomeViewModel,
    authViewModel: AuthViewModel,

    ) {
    when (selectedIndex) {
        0 -> HomePage(
            modifier = Modifier.fillMaxSize(),

            homeViewModel = homeViewModel
        )

        1 -> FavoritesPage(


            homeViewModel = homeViewModel

        )
        2 -> ProfilePage(


            authViewModel = authViewModel,
            homeViewModel = homeViewModel
        )



    }
}


data class NavItems(val title: String, val filledIcon: ImageVector, val outlinedIcon: ImageVector)