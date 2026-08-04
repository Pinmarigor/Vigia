package github.com.pinmarigor.vigia.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NominatimAddress(
    val city: String? = null,
    val town: String? = null,
    val village: String? = null,
    val suburb: String? = null,
    val road: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postcode: String? = null
)