package project.bancorym.projectRYM.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import project.bancorym.R
import project.bancorym.projectRYM.ui.models.InfoUser
import project.bancorym.projectRYM.navigation.AppScreens
import project.bancorym.projectRYM.repository.RetrofitClient
import project.bancorym.projectRYM.ui.models.DataMovimientos
import project.bancorym.projectRYM.ui.models.DataTarjetas
import project.bancorym.projectRYM.ui.models.DataUsuario

@Composable
fun RegisterScreen(infoUser: InfoUser, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var numero_tarjeta by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var tarjetaInput by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val isFormValid = name.isNotBlank() && apellidos.isNotBlank() && contraseña.isNotBlank() &&
            correo.isNotBlank() && celular.isNotBlank() && tarjetaInput.length == 16

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondoregister),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = contraseña,
                onValueChange = { contraseña = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo Electrónico") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = celular,
                onValueChange = { celular = it },
                label = { Text("Celular") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = tarjetaInput,
                onValueChange = {
                    if (it.length <= 16) {
                        tarjetaInput = it
                        numero_tarjeta = it.toIntOrNull() ?: 0
                    }
                },
                label = { Text("Número de Tarjeta") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (isFormValid) {


                        val newUser = DataUsuario(
                            name = name,
                            apellidos = apellidos,
                            contraseña = contraseña,
                            correo = correo,
                            celular = celular,
                            id = 0
                        )

                        coroutineScope.launch {
                            infoUser.savePersonData(name, apellidos, contraseña, correo, celular, numero_tarjeta)
                            try {
                                val response = RetrofitClient.webService.addUser(newUser)

                                if (response.isSuccessful) {
                                    val usuarioResponse = response.body()

                                    val userId = usuarioResponse?.data?.id

                                    val tarjeta = DataTarjetas(
                                        id = 0,
                                        numerotarjeta = tarjetaInput,
                                        idusuario = userId ?: 0
                                    )

                                    val tarjetaResponse = RetrofitClient.webService.addTarjeta(tarjeta)

                                    if (tarjetaResponse.isSuccessful) {
                                        val tarjetaResponseBody = tarjetaResponse.body()
                                        val tarjetaId = tarjetaResponseBody?.data?.id

                                        val movimiento = DataMovimientos(
                                            id = 0,
                                            tipomovimiento = "Creación de Tarjeta",
                                            idusuario = userId ?: 0,
                                            idtarjeta = tarjetaId ?: 0
                                        )

                                        val movimientoResponse = RetrofitClient.webService.AddMovimientos(movimiento)

                                        if (movimientoResponse.isSuccessful) {
                                            Log.d("Movimiento registrado", "Movimiento registrado con éxito")
                                        } else {
                                            Log.e("Error al registrar movimiento", "Error: ${movimientoResponse.errorBody()?.string()}")
                                        }
                                        Log.d("Tarjeta registrada", "Tarjeta registrada con éxito")
                                    } else {
                                        Log.e("Error al registrar tarjeta", "Error: ${tarjetaResponse.errorBody()?.string()}")
                                    }
                                    navController.popBackStack()
                                    navController.navigate(AppScreens.MainScreen.route)
                                } else {
                                    Log.e("Error al guardar usuario", "Error: ${response.errorBody()?.string()}")
                                    showDialog = true
                                }
                            } catch (e: Exception) {
                                Log.e("Error Retrofit", "Excepción: ${e.localizedMessage}")
                                showDialog = true
                            }
                        }
                    } else {
                        showDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registrar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
}
