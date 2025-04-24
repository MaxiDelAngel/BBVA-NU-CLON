package project.bancorym.projectRYM.ui.views

import android.util.Log
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import project.bancorym.projectRYM.ui.models.InfoUser
import project.bancorym.projectRYM.navigation.AppScreens

@Composable
fun RegisterScreen(infoUser: InfoUser, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var tarjeta by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var tarjetaInput by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() && apellidos.isNotBlank() && contrasena.isNotBlank() &&
            correo.isNotBlank() && celular.isNotBlank() && tarjetaInput.length == 16

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registro", textAlign = TextAlign.Center, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = celular,
            onValueChange = { celular = it },
            label = { Text("Celular") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = tarjetaInput,
            onValueChange = {
                if (it.length <= 16) {
                    tarjetaInput = it
                    tarjeta = it.toIntOrNull() ?: 0
                }
            },
            label = { Text("Número de Tarjeta") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isFormValid) {
                    coroutineScope.launch {
                        Log.d("Datos Guardados", "Nombre: $name, Apellidos: $apellidos, Contraseña: $contrasena, Correo: $correo, Celular: $celular, Tarjeta: $tarjeta")
                        infoUser.savePersonData(name, apellidos, contrasena, correo, celular, tarjeta)
                        navController.popBackStack()
                        navController.navigate(AppScreens.MainScreen.route)
                    }
                } else {
                    showDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Formulario Incompleto") },
                text = { Text("Te falta llenar algunos campos") },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}