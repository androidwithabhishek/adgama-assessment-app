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
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.Book
import gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen.HomeViewModel
import gupta.abhishek.adgamaassessmentapp.ui.navigation.GlobalNavControllerHolder
import gupta.abhishek.adgamaassessmentapp.ui.navigation.Routes

@Composable
fun HomePage(
    modifier: Modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()),
    homeViewModel: HomeViewModel,
) {
    homeViewModel.fetchBooks()
    val books by homeViewModel.books.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) { book ->
            BookCard(book = book)
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .clickable(onClick = {
                GlobalNavControllerHolder.navController.navigate(
                    Routes.DetailScreen(
                        title = book.title,
                        author = book.author,
                        coverUrl = book.coverUrl,
                        description = book.description,
                        publishedYear = book.publishedYear,
                        genre = book.genre
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
                model = book.coverUrl,
                contentDescription = book.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Text(
                text = book.author,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        }
    }
}
