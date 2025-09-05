package gupta.abhishek.adgamaassessmentapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import gupta.abhishek.adgamaassessmentapp.R
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Routes

@Composable
fun AuthScreen(modifier: Modifier = Modifier, authViewModel: AuthViewModel,navController: NavController)
{
    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()), // Add scroll if content is long
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        // Logo/Header Section
        Image(painter = painterResource(R.drawable.hd),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(bottom = 32.dp))

        // Title Section
        Text(text = "Start your reading journey now",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp, // Slightly smaller for better fit
            ),
            modifier = Modifier.padding(bottom = 8.dp))
        Spacer(modifier= Modifier.height(18.dp))
        Text(text = "Best online book reading platform with best collection",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier.padding(bottom = 32.dp))

        // Buttons Section
        Button(onClick = { navController.navigate(Routes.LogInScreen)}, modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)) {
            Text("Log In", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = {navController.navigate(Routes.SignIn) },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)) {
            Text("Sign In", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))




        Text(text = "You can sign in with",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier.padding(bottom = 16.dp))

        // Social Login Section
        SocialLoginRow(authViewModel = authViewModel)
    }
}

@Composable
fun SocialLoginRow(modifier: Modifier = Modifier, authViewModel: AuthViewModel)
{
    val context = LocalContext.current

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        listOf(Pair(R.drawable.ic_google) {

        },
            Pair(R.drawable.ic_facebook) { /* Handle Facebook */ },
            Pair(R.drawable.tw) { /* Handle Twitter */ }).forEach { (iconRes, onClick) ->
            Image(painter = painterResource(iconRes),
                contentDescription = "Social Login",
                modifier = Modifier
                    .size(48.dp)
                    .clickable(onClick = onClick))
        }
    }
}