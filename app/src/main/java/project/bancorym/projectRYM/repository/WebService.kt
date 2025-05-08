package project.bancorym.projectRYM.repository

import project.bancorym.projectRYM.domain.MovimientosResponse
import project.bancorym.projectRYM.domain.TarjetasResponse
import project.bancorym.projectRYM.domain.UsuarioResponse
import project.bancorym.projectRYM.ui.models.DataMovimientos
import project.bancorym.projectRYM.ui.models.DataTarjetas
import project.bancorym.projectRYM.ui.models.DataUsuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

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

    /*
    @POST("/movimientos/get")
    suspend fun getMovimientos(
        @Body tarjetaData: DataMovimientos
    ): Response<MovimientosResponse>
    */
}