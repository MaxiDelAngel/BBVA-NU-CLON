package project.bancorym.navigation

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController

class MyBiometricViewModel: ViewModel() {

    //variable que guardará el estado si debe mostrar o no el boton de autenticacion con biometricos
    var showBiometricOption by mutableStateOf(false)
        private set
    /*
    variable que informará si fue:
     1 -éxitoso ,
     0 - fallido ó
     -1 el usuario aún no ha intentado autenticarse con biometricos (predeterminado)
     */
    var biometricLogInSuccessful by mutableStateOf(-1)
        private set

    fun mostrarResultado(): String{
        return when(biometricLogInSuccessful){
            0 -> "Fallo autenticacón"
            1 -> "Exito con biometrico"
            else -> ""
        }
    }


    /*Revisa si está disponible , habilitado y compatible el dispositivo con la tecnología de biometria*/
    fun revisarCompatibilidad(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                // Biometric features are available
                showBiometricOption = true
                true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                // No biometric features available on this device
                false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                // Biometric features are currently unavailable.
                false
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                // Biometric features available but a security vulnerability has been discovered
                false
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                // Biometric features are currently unavailable because the specified options are incompatible with the current Android version..
                false
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                // Unable to determine whether the user can authenticate using biometrics
                false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // The user can't authenticate because no biometric or device credential is enrolled.
                false
            }

            else -> { false }
        }
    }

    fun autenticar(activity: FragmentActivity, context: Context, navController: NavController) {
        biometricLogInSuccessful = -1
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    biometricLogInSuccessful = 0
                    Log.d("BIOMETRIA", "Error es $errString - $errorCode")
                    Toast.makeText(context, "Error de autenticacion $errorCode", Toast.LENGTH_LONG).show()

                }

                @RequiresApi(Build.VERSION_CODES.R)
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    biometricLogInSuccessful = 1
                    Toast.makeText(context, "EXITO", Toast.LENGTH_LONG).show()
                    navController.navigate(AppScreens.MainScreen.route)
                    /*Aquí ya pudo reconocer la huella entonces aplicar logica por ejemplo que la functio autenticar reciba el navcontroller para así

                    navController.navigate(HOME)
                     */
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    biometricLogInSuccessful = 0
                    Log.d("BIOMETRIA", "NO fue posible leer la huella")
                    Toast.makeText(context, "NO fue posible leer la huella", Toast.LENGTH_LONG).show()

                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .setTitle("Autenticación con Biometricos")
            .setSubtitle("Inicia sesión con tus datos biometricos")
            .setNegativeButtonText("Usar contraseña")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}