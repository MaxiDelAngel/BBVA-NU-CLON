package project.bancorym.projectRYM.ui.models

import com.google.gson.annotations.SerializedName

data class DataTarjetas(
    @SerializedName("id") val id: Int,
    @SerializedName("numero_tarjeta") val numerotarjeta: String,
    @SerializedName("id_usuario") val idusuario: Int,
)
