package project.bancorym.projectRYM.domain

import com.google.gson.annotations.SerializedName
import project.bancorym.projectRYM.ui.models.DataTarjetas

data class TarjetasResponse(
    @SerializedName("code") var code: String = "",
    @SerializedName("mensaje") var mensaje: String = "",
    @SerializedName("data") var data: DataTarjetas? = null,
)
