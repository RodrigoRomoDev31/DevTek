package com.romvaz.core.data.utils

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

// A custom serializer for HardUserPreferenceModel to read and write preferences to/from a stream.
object AppPreferencesSerializer : Serializer<HardUserPreferenceModel> {

    // The default value for the HardUserPreferenceModel. If deserialization fails, this value is returned.
    override val defaultValue: HardUserPreferenceModel
        get() = HardUserPreferenceModel()

    // Function to read a HardUserPreferenceModel from an InputStream.
    override suspend fun readFrom(input: InputStream): HardUserPreferenceModel {
        return try {
            Json.decodeFromString(
                deserializer = HardUserPreferenceModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            Log.d(TAG, e.message ?: "Error during deserialization")
            defaultValue
        }
    }

    // Function to write a HardUserPreferenceModel to an OutputStream.
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
