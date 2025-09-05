package gupta.abhishek.adgamaassessmentapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import gupta.abhishek.adgamaassessmentapp.R
import gupta.abhishek.adgamaassessmentapp.ui.navigation.GlobalNavControllerHolder
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Routes
import gupta.abhishek.adgamaassessmentapp.utils.AppUtils
import kotlinx.coroutines.delay

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }



    val context = LocalContext.current

    val textColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colorScheme.onBackground

    } else {


        MaterialTheme.colorScheme.primary


    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 62.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Header Section
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text(
                text = "Hello User!",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 8.dp), color = textColor
            )

            Text(
                text = "Log In To Your Account",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp, color = textColor
            )
        }

        // Image
        Image(
            painter = painterResource(R.drawable.log_in),
            contentDescription = "Login illustration",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )

        // Form Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            // Password Field
            var passwordVisible by remember { mutableStateOf(false) }

            LaunchedEffect(passwordVisible) {
                if (passwordVisible) { // Only start delay if isLoading is true
                    delay(3000)
                    passwordVisible = !passwordVisible // Reset after 3 seconds
                }
            }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                if (passwordVisible) R.drawable.visible
                                else R.drawable.baseline_visibility_off_24
                            ),
                            contentDescription = if (passwordVisible) "Hide password"
                            else "Show password"
                        )
                    }
                })
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = {
                authViewModel.logIn(email = email, password = password) { message, booleanvlue ->

                    if (booleanvlue) {
                        navController.navigate(Routes.HomeScreen)
                        AppUtils.ShowToast(context, message)
                    } else {
                        AppUtils.ShowToast(context, message)
                    }

                }
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Log In", fontSize = 18.sp)
        }


        TextButton(
            onClick = { GlobalNavControllerHolder.navController.navigate(Routes.SignIn) },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(
                "Create a new account",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF87CEEB)
            )
        }

        Spacer(modifier = Modifier.height(20.dp)) // Bottom padding
    }
}