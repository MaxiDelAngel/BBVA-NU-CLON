package project.bancorym.projectRYM.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import project.bancorym.projectRYM.ui.models.InfoUser

@Composable
fun MainScreen(infoUser: InfoUser) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val savedName by infoUser.name.collectAsState(initial = "")
        val savedLastName by infoUser.lastName.collectAsState(initial = "")
        val savedPassword by infoUser.password.collectAsState(initial = "")
        val savedEmail by infoUser.email.collectAsState(initial = "")
        val savedPhone by infoUser.phone.collectAsState(initial = "")
        val savedCard by infoUser.card.collectAsState(initial = 0)


        Text(text="Bienvenido")
    }
}