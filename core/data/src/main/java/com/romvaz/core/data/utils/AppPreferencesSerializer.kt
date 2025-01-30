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
        get() = HardUserPreferenceModel() // Return a new instance as default if deserialization fails.

    // Function to read a HardUserPreferenceModel from an InputStream.
    override suspend fun readFrom(input: InputStream): HardUserPreferenceModel {
        return try {
            // Deserialize the InputStream to a HardUserPreferenceModel object using the JSON serializer.
            Json.decodeFromString(
                deserializer = HardUserPreferenceModel.serializer(),
                string = input.readBytes().decodeToString() // Convert the InputStream bytes to a String and decode it.
            )
        } catch (e: SerializationException) {
            // If deserialization fails, log the error and return the default value.
            Log.d(TAG, e.message ?: "Error during deserialization")
            defaultValue // Return the default value if deserialization fails.
        }
    }

    // Function to write a HardUserPreferenceModel to an OutputStream.
    override suspend fun writeTo(t: HardUserPreferenceModel, output: OutputStream) {
        // Use IO dispatcher for background IO operations.
        withContext(Dispatchers.IO) {
            // Serialize the HardUserPreferenceModel object to a JSON string and write it to the OutputStream.
            output.write(
                Json.encodeToString(
                    serializer = HardUserPreferenceModel.serializer(),
                    value = t // The object to be serialized.
                ).encodeToByteArray() // Convert the JSON string to bytes before writing.
            )
        }
    }
}
