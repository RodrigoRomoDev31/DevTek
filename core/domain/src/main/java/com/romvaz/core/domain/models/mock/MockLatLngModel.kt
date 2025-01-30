package com.romvaz.core.domain.models.mock

import com.google.android.gms.maps.model.LatLng

// Model To Mock a List For User Location
// Remove in real movement situations
data class MockLatLngModel(
    val latLng: List<LatLng>
) {
    companion object {
        // Mock List for Maps to Use
        // THIS IS ONLY FOR DEBUG SITUATIONS
        val mockListLatLng = listOf(
            // Mexico
            LatLng(19.4326, -99.1332),  // Mexico City, CDMX
            LatLng(19.0414, -98.2063),  // Puebla, Puebla
            LatLng(18.8510, -97.1036),  // Orizaba, Veracruz
            LatLng(18.8850, -96.9356),  // Córdoba, Veracruz
            LatLng(17.9686, -94.5260),  // Acayucan, Veracruz
            LatLng(17.9614, -94.0935),  // Las Choapas, Veracruz
            LatLng(17.9895, -92.9281),  // Villahermosa, Tabasco
            LatLng(18.0019, -93.3745),  // Cárdenas, Tabasco
            LatLng(17.4957, -91.9823),  // Tenosique, Tabasco
            LatLng(16.7370, -92.6376),  // Comitán, Chiapas
            LatLng(16.7539, -93.1157),  // Tuxtla Gutiérrez, Chiapas

            // Central America
            LatLng(14.6349, -90.5069),  // Guatemala City, Guatemala
            LatLng(13.6929, -89.2182),  // San Salvador, El Salvador
            LatLng(9.9325, -84.0796),   // San José, Costa Rica
            LatLng(8.9824, -79.5199),   // Panama City, Panama

            // South America
            LatLng(3.4372, -76.5225),   // Cali, Colombia
            LatLng(-0.1807, -78.4678),  // Quito, Ecuador
            LatLng(-12.0464, -77.0428), // Lima, Peru
            LatLng(-16.5000, -68.1500), // La Paz, Bolivia
            LatLng(-24.7761, -65.4095), // Salta, Argentina
            LatLng(-31.4201, -64.1888), // Córdoba, Argentina
            LatLng(-34.6037, -58.3816)  // Buenos Aires, Argentina
        )
    }
}
