package gupta.abhishek.adgamaassessmentapp.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import gupta.abhishek.adgamaassessmentapp.ui.auth.authModel.UserDetails

class AuthViewModel : ViewModel() {


    val db = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun logoutUser() {

       auth.signOut()

    }

    fun signUp(
        email: String,
        password: String,
        name: String,
        onSuccess: (String, Boolean) -> Unit,

        ) {


        try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {


                    val user = auth.currentUser
                    val userId = user?.uid



                    if (userId != null) {


                        val userDetail = UserDetails(
                            name = name,
                            email = email,
                            uid = userId,
                            passkey = password,
                        )


                        db.collection("users").document(userId).set(userDetail)
                            .addOnSuccessListener {
                                onSuccess("Your Account Is Created", true)
                            }.addOnFailureListener { exception ->

                            }
                    }


                } else {
                    val errorMessage = when (task.exception) {


                        is FirebaseAuthUserCollisionException -> {
                            "  This email is already registered"
                        }

                        is FirebaseAuthWeakPasswordException -> {
                            "Invalid email format"
                        }

                        else -> {

                            task.exception?.localizedMessage ?: "Signup failed"


                        }
                    }

                    onSuccess(errorMessage, false)


                }
            }
        } catch (e: Exception) {
            onSuccess("Unexpected error: ${e.localizedMessage}", false)
        }
    }

    fun logIn(email: String, password: String, onSuccess: (String, Boolean) -> Unit) {


        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess(" Welcome User ", true)
                } else {
                    val errorMessage = when (task.exception) {


                        is FirebaseAuthInvalidUserException -> {
                            "No account found with this email."
                        }

                        is FirebaseAuthInvalidCredentialsException -> {
                            "Incorrect password."
                        }


                        else -> {
                            task.exception?.message ?: "Login failed. Please try again."
                        }
                    }

                    onSuccess(errorMessage, false)
                }
            }
        } catch (e: Exception) {

            onSuccess("oops something went wrong", false)
        }


    }



}






