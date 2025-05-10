package project.bancorym.projectRYM.repository

import project.bancorym.projectRYM.domain.GetInfoTarjetas
import project.bancorym.projectRYM.domain.MovimientosResponse
import project.bancorym.projectRYM.domain.TarjetasResponse
import project.bancorym.projectRYM.domain.UsuarioResponse
import project.bancorym.projectRYM.ui.models.DataGetTarjeta
import project.bancorym.projectRYM.ui.models.DataMovimientos
import project.bancorym.projectRYM.ui.models.DataTarjetas
import project.bancorym.projectRYM.ui.models.DataUsuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WebService {
    @POST("/usuarios/add")
    suspend fun addUser(
        @Body userData: DataUsuario
    ): Response<UsuarioResponse>

    @POST("/tarjetas/add")
    suspend fun addTarjeta(
        @Body tarjetaData: DataTarjetas
    ): Response<TarjetasResponse>

    @POST("/movimientos/add")
    suspend fun AddMovimientos(
        @Body movimientoData: DataMovimientos
    ): Response<MovimientosResponse>

    @GET("/tarjeta/numero")
    suspend fun getInfoTarjeta(
        @Query("numero_tarjeta") numeroTarjeta: String
    ): Response<GetInfoTarjetas>
}