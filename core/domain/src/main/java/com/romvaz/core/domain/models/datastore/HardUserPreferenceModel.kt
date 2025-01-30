package com.romvaz.core.domain.models.datastore

import com.romvaz.core.domain.models.api.requests.CurrentLocationModel
import com.romvaz.core.domain.models.api.requests.SendHelpPostModel
import kotlinx.serialization.Serializable
import java.util.UUID

// User Preference model
// Mostly HardCoded for Mock Login
@Serializable
data class HardUserPreferenceModel(
    val email: String = "",
    val name: String = "Rodrigo Romo Vázquez",
    val plate: String = "TKY-28-73",
    val conductorId: String = UUID.randomUUID().toString(),
    val phoneNumber: String = "961-329-0097",
    val truckModel: String = "BM000558",
    val truckId: String = UUID.randomUUID().toString()
)

// Extension function to map HardUserPreferenceModel into SendHelpPostModel
fun HardUserPreferenceModel.createPostModel(lat: Double, lng: Double, problem: String): SendHelpPostModel =
    SendHelpPostModel(
        operatorName = this.name,
        operatorId = this.conductorId,
        operatorPhone = this.phoneNumber,
        operatorTruckId = this.truckId,
        lastLocation = CurrentLocationModel(lat, lng),
        problem = problem
    )
