package ran.am.crudoperationfirestore

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveContact(name: String, mobile: String, password: String, onComplete: (Boolean) -> Unit) {
        val contact = hashMapOf("name" to name, "mobile" to mobile, "password" to password)
        db.collection("contacts").document(mobile).set(contact)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

   fun retrievePassword(mobile: String, onComplete: (String?) -> Unit) {
        db.collection("contacts").document(mobile).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    onComplete(document.getString("password"))
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener { onComplete(null) }
    }

        fun updatePassword(mobile: String, newPassword: String, onComplete: (Boolean) -> Unit) {
            db.collection("contacts").document(mobile).update("password", newPassword)
                .addOnSuccessListener { onComplete(true) }
                .addOnFailureListener { onComplete(false) }
        }

        fun deleteContact(mobile: String, onComplete: (Boolean) -> Unit) {
            db.collection("contacts").document(mobile).delete()
                .addOnSuccessListener { onComplete(true) }
                .addOnFailureListener { onComplete(false) }
        }
}
