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

    companion object { //para que sea singleton el acceso al archivo
        val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore(name = "settings")

        val AGE = intPreferencesKey("edad")
        val NAME = stringPreferencesKey("nombre")
    }

    //Flow parte de las corrutinas para transmiti(recibir) el flujo de datos tipo Entero de forma asincrona
    val age: Flow<Int> = context.dataStore.data.map { informacion -> // No type safety.
        informacion[AGE] ?: 0
    }
    //Flow parte de las corrutinas para transmiti(recibir) el flujo de
    // datos tipo String de forma asincrona
    val name: Flow<String> = context.dataStore.data.map{ informacion -> informacion[NAME] ?: "" }

    suspend fun savePersonData(personName: String, personAge: Int) {
        context.dataStore.edit { settings ->
            settings[AGE] = personAge
            settings [NAME] = personName

        }
    }
}
