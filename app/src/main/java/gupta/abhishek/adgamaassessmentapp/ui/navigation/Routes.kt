package gupta.abhishek.adgamaassessmentapp.ui.navigation

import kotlinx.serialization.Serializable


sealed class Routes {


    @Serializable
    data object AuthScreen : Routes()

    @Serializable
    data object LogInScreen : Routes()


    @Serializable
    data object SignIn : Routes()


    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data class DetailScreen(val title: String,
                            val author: String,
                            val coverUrl: String,
                            val description: String,
                            val publishedYear: Int,
                            val genre: String ) : Routes()




}