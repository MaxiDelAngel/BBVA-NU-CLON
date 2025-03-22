package project.bancorym

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import project.bancorym.models.InfoUser

@Composable
fun MainScreen(infoUser: InfoUser) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutineScope = rememberCoroutineScope()

        var name by remember { mutableStateOf("") }
        var age by remember { mutableIntStateOf(0) }
        var showDialog by remember { mutableStateOf(false) }

        val savedName by infoUser.name.collectAsState(initial = "")
        val savedAge by infoUser.age.collectAsState(initial = 0)

        // Solo mostrar el diálogo si el nombre guardado está vacío
        LaunchedEffect(savedName) {
            if (savedName.isEmpty()) {
                showDialog = true
            }
        }

        if (savedName.isNotEmpty()) {
            Text(text = "Bienvenido, $savedName")
        }else if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Ingrese su nombre") },
                text = {
                    Column {
                        BasicTextField(
                            value = name,
                            onValueChange = { name = it },
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        coroutineScope.launch {
                            infoUser.savePersonData(name, age)
                            showDialog = false
                        }
                    }) {
                        Text("Guardar")
                    }
                }
            )
        }
    }
}