package github.com.pinmarigor.vigia.network.dto

import com.google.gson.annotations.SerializedName
import github.com.pinmarigor.vigia.network.model.NominatimAddress


data class NominatimResponse(
    @SerializedName("display_name")
    val displayName: String,
    val address: NominatimAddress,
    val lat: String,
    val lon: String,
)