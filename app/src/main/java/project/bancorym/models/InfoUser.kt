package project.bancorym.models

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class InfoUser (private val context: Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore(name = "settings")

        val NAME = stringPreferencesKey("nombre")
        val LASTNAME = stringPreferencesKey("apellidos")
        val PASSWORD = stringPreferencesKey("contrasena")
        val EMAIL = stringPreferencesKey("correo")
        val PHONE = stringPreferencesKey("celular")
        val CARD = intPreferencesKey("tarjeta")
    }

    val name: Flow<String> = context.dataStore.data.map{ informacion -> informacion[NAME] ?: "" }
    val lastName: Flow<String> = context.dataStore.data.map{ informacion -> informacion[LASTNAME] ?: "" }
    val password: Flow<String> = context.dataStore.data.map{ informacion -> informacion[PASSWORD] ?: "" }
    val email: Flow<String> = context.dataStore.data.map{ informacion -> informacion[EMAIL] ?: "" }
    val phone: Flow<String> = context.dataStore.data.map{ informacion -> informacion[PHONE] ?: "" }
    val card: Flow<Int> = context.dataStore.data.map{ informacion -> informacion[CARD] ?: 0 }



    suspend fun savePersonData(personName: String, personLastName: String, personPassword: String, personEmail: String, personPhone: String, personCard: Int) {
        context.dataStore.edit { settings ->
            settings [NAME] = personName
            settings [LASTNAME] = personLastName
            settings [PASSWORD] = personPassword
            settings [EMAIL] = personEmail
            settings [PHONE] = personPhone
            settings [CARD] = personCard
        }
    }

    suspend fun clearData() {
        context.dataStore.edit { settings ->
            settings.clear()
        }
    }
}
