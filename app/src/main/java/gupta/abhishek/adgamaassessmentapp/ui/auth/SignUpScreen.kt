package gupta.abhishek.adgamaassessmentapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Routes
import gupta.abhishek.adgamaassessmentapp.utils.AppUtils


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val textColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colorScheme.onBackground
        // White or light color on dark background
    } else {

        MaterialTheme.colorScheme.primary // Black or dark color on light background
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 62.dp)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures { keyboardController?.hide() }
            },
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp)) // Top padding

        // Header Section
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text(
                text = "Hello User!",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Log In To Your Account",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        // Image
        Image(
            painter = painterResource(R.drawable.sigh_in),
            contentDescription = "Sign up illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentScale = ContentScale.Fit
        )

        // Form Fields
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },


            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
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

        Spacer(modifier = Modifier.height(8.dp))

        // Sign Up Button
        Button(
            onClick = {
                authViewModel.signUp(
                    name = name,
                    email = email,
                    password = password,
                    onSuccess = { message, booleanvlue ->

                        if (booleanvlue) {
                            navController.navigate(Routes.HomeScreen)
                            AppUtils.ShowToast(context, message)
                        } else {
                            AppUtils.ShowToast(context, message)
                        }

                    })
            }, enabled = !isLoading, modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Sign Up", fontSize = 18.sp)
            }
        }

        // Login Option
        TextButton(
            onClick = { navController.navigate(Routes.LogInScreen) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                "Already have an account ? Log in",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF87CEEB)
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Bottom padding
    }
}