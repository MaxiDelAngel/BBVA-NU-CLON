package project.bancorym.projectRYM.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bancorym.R
import project.bancorym.projectRYM.ui.models.InfoUser

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(infoUser: InfoUser) {
    var selectedItem by remember { mutableStateOf(0) }

    val savedName by infoUser.name.collectAsState(initial = "")
    val savedLastName by infoUser.lastName.collectAsState(initial = "")
    val savedPassword by infoUser.password.collectAsState(initial = "")
    val savedEmail by infoUser.email.collectAsState(initial = "")
    val savedPhone by infoUser.phone.collectAsState(initial = "")
    val savedCard by infoUser.card.collectAsState(initial = 0)

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = { Icon(Icons.Default.CreditCard, contentDescription = "Solicitar tarjeta") },
                    label = { Text("Solicitar") }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = { Icon(Icons.Default.Savings, contentDescription = "Cajita") },
                    label = { Text("Cajita") }
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(0xFF0061A8))
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jretta),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "icono",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Icon(
                        imageVector = Icons.Default.GroupAdd,
                        contentDescription = "icono",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .background(Color(0xFF0061A8))
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Bienvenido, $savedName",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Dinero Activo\n$0.00",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 5.dp, bottom = 25.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FeatureButton("Recibir")
                    FeatureButton("Transferir")
                    FeatureButton("Simular\npréstamo")
                    FeatureButton("Servicios\ny recargas")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "TARJETAS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    TarjetaCard(R.drawable.nu, "$savedName $savedLastName", "2347", "Debito")
                    TarjetaCard(R.drawable.bbva, "$savedName $savedLastName", "9315", "Crédito")
                }
            }
        }
    }
}

@Composable
fun FeatureButton(label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0F0FF))
        ) {
            Icon(
                imageVector = when (label) {
                    "Recibir" -> Icons.Default.GetApp
                    "Transferir" -> Icons.Default.Contactless
                    "Simular\npréstamo" -> Icons.Default.CurrencyExchange
                    "Servicios\ny recargas" -> Icons.Default.Receipt
                    else -> Icons.Default.Help
                },
                contentDescription = "icono",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )
    }
}

@Composable
fun TarjetaCard(img: Int, nombre: String, terminacion: String, tipo: String) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(height = 80.dp, width = 125.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = tipo,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(text = nombre, color = Color.Black)
                Text(text = "•$terminacion", color = Color.Gray)
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}
