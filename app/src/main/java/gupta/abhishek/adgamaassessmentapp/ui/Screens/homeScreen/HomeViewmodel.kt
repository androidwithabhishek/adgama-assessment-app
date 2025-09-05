package gupta.abhishek.adgamaassessmentapp.ui.Screens.homeScreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import gupta.abhishek.adgamaassessmentapp.utils.AppUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class HomeViewModel(

) : ViewModel() {

    private val _homeUserData = MutableStateFlow(HomeUserDetails())
    val homeUserData: StateFlow<HomeUserDetails> = _homeUserData


    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _getFavBooks = MutableStateFlow<List<GetFavBooks>>(emptyList())
    val getFavBooks: StateFlow<List<GetFavBooks>> = _getFavBooks




    private val homeDb: FirebaseFirestore = FirebaseFirestore.getInstance()


    fun fetchUserData() {
        viewModelScope.launch {
            try {

                val homeUserDatas =
                    homeDb.collection("users").document(FirebaseAuth.getInstance().currentUser?.uid!!)
                        .get().await()
                        .toObject(HomeUserDetails::class.java) ?: return@launch


                _homeUserData.value = homeUserDatas
                Log.d("USERDATA", "Fetched user: $homeUserData")
            } catch (e: Exception) {
                Log.e("USERDATA_ERROR", "Error fetching user data", e)
            }
        }
    }


    fun fetchBooks() {
        viewModelScope.launch {

            try {
                val snapshot = homeDb.collection("books").get().await()
                val bookList = snapshot.toObjects(Book::class.java)
                _books.value = bookList
            } catch (e: Exception) {
                Log.e("BOOKS_ERROR", "Error fetching books", e)
            }

        }
    }


    fun addBookToFavorites(
        title: String,
        author: String,
        coverUrl: String,
        description: String,
        publishedYear: Int,
        genre: String,
        context: Context,

        ) {

        val favBooks = FavBooks(
            title = title,
            author = author,
            coverUrl = coverUrl,
            description = description,
            publishedYear = publishedYear,
            genre = genre
        )
        try {
            homeDb.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                .collection("favorites")
                .add(favBooks).addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        AppUtils.ShowToast(context, "Book added to favorites")
                    } else {
                        AppUtils.ShowToast(context, "Failed to add favorite")
                    }

                }
        } catch (e: Exception) {
            AppUtils.ShowToast(context, e.localizedMessage.toString())
        }


    }

    fun fetchBooksFavBooks() {
        viewModelScope.launch {
            try {

                val snapshot = homeDb.collection("users")
                    .document(FirebaseAuth.getInstance().currentUser?.uid!! )
                    .collection("favorites").get().await()
                val FBList = snapshot.toObjects(GetFavBooks::class.java)
                _getFavBooks.value = FBList

            } catch (e: Exception) {

            }
        }
    }


}

data class HomeUserDetails(
    val name: String = "", val email: String = "", val uid: String = "", val passkey: String = "",
)

data class Book(
    val title: String = "",
    val author: String = "",
    val coverUrl: String = "",
    val description: String = "",
    val publishedYear: Int = 0,
    val genre: String = "",
)

data class FavBooks(
    val title: String,
    val author: String,
    val coverUrl: String,
    val description: String,
    val publishedYear: Int,
    val genre: String,
)

data class GetFavBooks(
    val title: String = "",
    val author: String = "",
    val coverUrl: String = "",
    val description: String = "",
    val publishedYear: Int = 0,
    val genre: String = "",
)


