package gupta.abhishek.adgamaassessmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.ui.auth.AuthViewModel
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Navigation
import gupta.abhishek.adgamaassessmentapp.ui.theme.AdgamaAssessmentAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val authViewModel: AuthViewModel = viewModel()
            val homeViewModel: HomeViewModel = viewModel()
            AdgamaAssessmentAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        Navigation(
                            modifier = Modifier.padding(innerPadding).padding(WindowInsets.systemBars.asPaddingValues()),
                            authViewModel = authViewModel,
                            homeViewModel =homeViewModel
                        )



                    }


                }
            }
        }
    }

    @Composable
    fun Greeting(modifier: Modifier) {
    }