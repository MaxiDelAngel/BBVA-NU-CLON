package project.bancorym.projectRYM.ui.models

import com.google.gson.annotations.SerializedName

data class DataUsuario(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("apellidos") val apellidos: String,
    @SerializedName("contraseña") val contraseña: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("celular") val celular: String,
)
