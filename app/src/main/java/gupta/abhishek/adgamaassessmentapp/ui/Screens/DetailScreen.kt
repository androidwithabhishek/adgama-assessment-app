
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.utils.AppUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    title: String,
    author: String,
    coverUrl: String,
    description: String,
    publishedYear: Int,
    genre: String,
    homeViewModel: HomeViewModel,
) {

    val context = LocalContext.current
    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()

                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = coverUrl,
                    contentDescription = "$title Cover",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2f / 3f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                // Author
                Text(
                    text = "by $author",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Year + Genre
                Text(
                    text = "$publishedYear • $genre",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
            }

            FloatingActionButton(
                onClick = {
                    AppUtils.ShowToast(
                        context = context,
                        message = "Added to favorite page",
                    )
                    homeViewModel.addBookToFavorites(
                        title = title,
                        author = author,
                        coverUrl = coverUrl,
                        description = description,
                        publishedYear = publishedYear,
                        genre = genre,
                        context = context
                    )

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 48.dp, bottom = 32.dp)
            ) {
                Icon(
                    imageVector = if (true) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }


        }

    }
}






