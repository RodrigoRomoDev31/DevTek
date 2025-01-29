package com.romvaz.core.domain.models

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
