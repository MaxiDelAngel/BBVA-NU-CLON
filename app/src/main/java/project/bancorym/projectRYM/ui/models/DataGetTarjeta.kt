package project.bancorym.projectRYM.ui.models

import com.google.gson.annotations.SerializedName

data class DataGetTarjeta(
    @SerializedName("tarjeta_id") val tarjeta_id: Int,
    @SerializedName("numero_tarjeta") val numero_tarjeta: String,
    @SerializedName("dinero") val dinero: Float,
    @SerializedName("cvv") val cvv: Int,
    @SerializedName("fecha_caducidad") val fecha_caducidad: String,
    @SerializedName("nombre_usuario") val nombre_usuario: String,
    @SerializedName("correo") val correo: String,
)
