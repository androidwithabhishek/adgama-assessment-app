package gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gupta.abhishek.adgamaassessmentapp.R
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.ui.auth.AuthViewModel

@Composable
fun ProfilePage(authViewModel: AuthViewModel, homeViewModel: HomeViewModel) {

    homeViewModel.fetchUserData()
    val user by homeViewModel.homeUserData.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize() // make Column fill the screen
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )


        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )


        Spacer(modifier = Modifier.height(8.dp))



        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp
            )
        )


        Button(
            onClick = {
                authViewModel.logoutUser()

            }
        ) {
            Text("Logout")
        }

    }


}