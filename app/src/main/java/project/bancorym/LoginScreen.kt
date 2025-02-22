package project.bancorym

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import project.bancorym.navigation.AppScreens

@Composable
fun LoginScreen(navController: NavController) {
    //authenticate: (auth: (Boolean) -> Unit) -> Unit
    var auth by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        //Primera parte del diseño
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fondologin),
                contentDescription = "Fondo",
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            color = Color(0xFF111D79),
                            alpha = 0.8f
                        )
                    },
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        //.background(Color.Red)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text="RYM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        //.background(Color.Green)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Bienvenido Usuario",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = "Cambiar de usuario",
                                color = Color(0xFF2196F3),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            )
                            Spacer(modifier = Modifier.size(30.dp))
                            Button(
                                onClick = {
                                    /*
                                    authenticate{
                                        auth = it
                                        if(auth){
                                            navController.navigate(AppScreens.MainScreen.route)
                                        } else {
                                            navController.navigate(AppScreens.LoginScreen.route)
                                        }
                                    }*/
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .shadow(5.dp, shape = RectangleShape),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = Color(0xFF2196F3)
                                ),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = "Acceder a tu cuenta",
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "icono",
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.TopEnd)
                            .padding(10.dp, top=30.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        //.background(Color.Black)
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "icono",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Text(
                            text = "Token Movil",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "icono",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Text(
                            text = "Operación QR",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "icono",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Text(
                            text = "Emergencia",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        } //Fin de la primera parte del diseño
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}