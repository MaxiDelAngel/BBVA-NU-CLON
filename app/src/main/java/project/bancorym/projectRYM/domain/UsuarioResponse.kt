package project.bancorym.projectRYM.domain

import com.google.gson.annotations.SerializedName
import project.bancorym.projectRYM.ui.models.DataUsuario

data class UsuarioResponse(
    @SerializedName("code") var code: String = "",
    @SerializedName("mensaje") var mensaje: String = "",
    @SerializedName("data") var data: DataUsuario? = null,
)
