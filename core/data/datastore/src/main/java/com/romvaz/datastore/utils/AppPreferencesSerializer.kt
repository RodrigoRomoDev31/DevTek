package com.romvaz.datastore.utils

import android.content.ContentValues.TAG
import android.util.Log
import androidx.datastore.core.Serializer
import com.romvaz.core.domain.models.datastore.HardUserPreferenceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AppPreferencesSerializer : Serializer<HardUserPreferenceModel> {
    override val defaultValue: HardUserPreferenceModel
        get() = HardUserPreferenceModel()

    override suspend fun readFrom(input: InputStream): HardUserPreferenceModel {
        return try {
            Json.decodeFromString(
                deserializer = HardUserPreferenceModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            Log.d(TAG, e.message ?: "")
            defaultValue
        }
    }

    override suspend fun writeTo(t: HardUserPreferenceModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = HardUserPreferenceModel.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
