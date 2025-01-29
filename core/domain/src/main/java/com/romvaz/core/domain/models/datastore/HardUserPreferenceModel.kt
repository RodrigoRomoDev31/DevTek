package com.romvaz.core.domain.models.datastore

import com.romvaz.core.domain.models.api.SendHelpPostModel
import kotlinx.serialization.Serializable
import java.util.UUID

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

fun HardUserPreferenceModel.createPostModel(): SendHelpPostModel =
    SendHelpPostModel(
        operatorName = this.name,
        operatorId = this.conductorId,
        operatorPhone = this.phoneNumber,
        operatorTruckId = this.truckId
    )
