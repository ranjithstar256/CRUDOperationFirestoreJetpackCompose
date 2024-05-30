package ran.am.crudoperationfirestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ran.am.crudoperationfirestore.ui.theme.CRUDOperationFirestoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CRUDOperationFirestoreTheme {
                ContactApp()
            }
        }
    }
}

@Composable
fun ContactApp(viewModel: ContactViewModel = ContactViewModel()) {

    var namee by remember {
        mutableStateOf("")
    }
    var mobilee by remember {mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var retrievedPassword by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = namee,
            onValueChange = { namee = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value =  mobilee,
            onValueChange = { mobilee = it },

            modifier = Modifier.fillMaxWidth(),

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),

            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.saveContact(namee, mobilee, password) }) {
            Text("Save")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.retrievePassword(mobilee){
            retrievedPassword = it
        } }) {
            Text("Retrieve Password")
        }
        Text(text = "Retrieved Password: $retrievedPassword")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("New Password") },
            modifier = Modifier.fillMaxWidth(),

            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.updatePassword(
            mobilee,password
        ) }) {
            Text("Update Password")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.deleteContact(mobilee) }) {
            Text("Delete Contact")
        }
    }
}
