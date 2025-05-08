package project.bancorym.projectRYM.ui.models

import com.google.gson.annotations.SerializedName

data class DataMovimientos(
    @SerializedName("id") val id: Int,
    @SerializedName("tipo_mov") val tipomovimiento: String,
    @SerializedName("id_usuario") val idusuario: Int,
    @SerializedName("id_tarjeta") val idtarjeta: Int,
)
