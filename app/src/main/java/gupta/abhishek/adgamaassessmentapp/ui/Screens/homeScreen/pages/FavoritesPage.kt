package gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.GetFavBooks
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.ui.navigation.GlobalNavControllerHolder
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Routes

@Composable
fun FavoritesPage(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {


    homeViewModel.fetchBooksFavBooks()
    val getFavBooksState by homeViewModel.getFavBooks.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(getFavBooksState) {favBook ->
            FaveBookCard(
                getFavBooks = favBook
            )
        }
    }

}

@Composable
fun FaveBookCard(getFavBooks: GetFavBooks) {
    Card(
        modifier = Modifier
            .clickable(onClick = {
                GlobalNavControllerHolder.navController.navigate(
                    Routes.DetailScreen(
                        title = getFavBooks.title,
                        author = getFavBooks.author,
                        coverUrl = getFavBooks.coverUrl,
                        description = getFavBooks.description,
                        publishedYear = getFavBooks.publishedYear,
                        genre = getFavBooks.genre
                    )
                )
            })
            .fillMaxWidth()
            .aspectRatio(0.6f),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage( // Coil image loader
                model = getFavBooks.coverUrl,
                contentDescription = getFavBooks.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = getFavBooks.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Text(
                text = getFavBooks.author,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        }
    }
}




