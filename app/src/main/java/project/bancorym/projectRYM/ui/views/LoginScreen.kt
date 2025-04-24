package project.bancorym.projectRYM.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import project.bancorym.R
import project.bancorym.projectRYM.ui.models.InfoUser
import project.bancorym.projectRYM.navigation.AppScreens
import project.bancorym.projectRYM.ui.viewsmodels.MyBiometricViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: MyBiometricViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var auth by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as FragmentActivity
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.gifwelcome))
    val infoUser = InfoUser(context)
    val savedName by infoUser.name.collectAsState(initial = "")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
        ) {
            // Primera parte del diseño
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(410.dp)
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
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RYM",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (savedName.isNotEmpty()){
                                    Text(
                                        text = stringResource(R.string.bienvenida_login) + savedName,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                    )
                                } else {
                                    Text(
                                        text = stringResource(R.string.bienvenido_usuario),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                    )
                                }
                                Spacer(modifier = Modifier.size(10.dp))
                                Text(
                                    text = stringResource(R.string.cambiar_de_usuario),
                                    color = Color(0xFF2196F3),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                                Spacer(modifier = Modifier.size(30.dp))
                                Button(
                                    onClick = {
                                        //viewModel.autenticar(activity, context, navController)
                                        auth = true
                                        navController.popBackStack()
                                        if (savedName.isNotEmpty()){
                                            navController.navigate(AppScreens.MainScreen.route)
                                        }else{
                                            navController.navigate(AppScreens.RegisterScreen.route)
                                        }
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
                                        text = stringResource(R.string.acceder_a_tu_cuenta),
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
                                .padding(10.dp, top = 30.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "icono Token Movil",
                                tint = Color(0xFF2196F3),
                                modifier = Modifier.size(60.dp).clickable { coroutineScope.launch{ infoUser.clearData() } }
                            )
                            Text(
                                text = stringResource(R.string.token_movil),
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
                                contentDescription = "icono operacion",
                                tint = Color(0xFF2196F3),
                                modifier = Modifier.size(60.dp)
                            )
                            Text(
                                text = stringResource(R.string.operacion_qr),
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
                                contentDescription = "icono emergencia",
                                tint = Color(0xFF2196F3),
                                modifier = Modifier.size(60.dp)
                            )
                            Text(
                                text = stringResource(R.string.emergencia),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            } // Fin de la primera parte del diseño
        }
        Column( // Segunda parte del diseño
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEAEAEA))
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    iterations = Int.MAX_VALUE,
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.hola_futuro_inversionista),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = buildString { // Para mejorar el manejo del string de Español a Ingles
                    append(stringResource(R.string.hoy_es_el_mejor_d_a_para_empezar_invierte_en_un_pagar))
                    append(stringResource(R.string.rym_y_asegura_rendimientos_desde_el_inicio_hazlo_en))
                    append(stringResource(R.string.oportunidades))
                },
                color = Color.DarkGray,
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEAEAEA))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(6.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Icono de Informacion",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.detecta_las_estafas_que_usan_los_delincuentes),
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.Tips_para_seguridad),
                            color = Color.DarkGray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        } // Fin de la segunda parte del diseño
    }
}

@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    val biometricViewModel: MyBiometricViewModel = viewModel()
    LoginScreen(navController, biometricViewModel)
}