package ran.am.crudoperationfirestore

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {

    private val repository = FirestoreRepository()

    var name by mutableStateOf("")
    var mobile by mutableStateOf("")
    var password by mutableStateOf("")
    var retrievedPassword by mutableStateOf("")

    fun saveContact(name :String, mobile :String, password : String) {
        repository.saveContact(name, mobile, password) { success ->
            if (success) {
                // Handle success

                Log.i("ContactViewModel", "Contact saved successfully")
            } else {
                // Handle failure
                Log.i("ContactViewModel", "Contact not saved successfully")

            }
        }
    }

    fun retrievePassword(mobile: String, function: (String) -> Unit) {
        repository.retrievePassword(mobile) {
            retrievedPwd ->

            retrievedPassword = retrievedPwd ?: "No password found"

            function(retrievedPassword)

            Log.i("qwertyContactViewModel", "Password retrieved successfully $retrievedPassword")
        }
    }

    fun updatePassword(mobile: String,newPassword: String) {
        repository.updatePassword(mobile, newPassword) { success ->
            if (success) {
                // Handle success
            } else {
                // Handle failure
            }
        }
    }

    fun deleteContact(mobile: String) {
        repository.deleteContact(mobile) { success ->
            if (success) {
                // Handle success
            } else {
                // Handle failure
            }
        }
    }
}
