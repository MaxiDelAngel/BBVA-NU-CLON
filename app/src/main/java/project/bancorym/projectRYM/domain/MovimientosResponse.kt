package project.bancorym.projectRYM.domain

import com.google.gson.annotations.SerializedName
import project.bancorym.projectRYM.ui.models.DataMovimientos

data class MovimientosResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("code") var code: String = "",
    @SerializedName("mensaje") var mensaje: String = "",
    @SerializedName("data") var data: DataMovimientos? = null,
)
