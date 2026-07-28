package github.com.pinmarigor.vigia.data.model

enum class Relationship {
    PAI, MAE, IRMAO, IRMA, MARIDO, ESPOSA, FILHO, FILHA, AVO, TIO, TIA, SOBRINHO, SOBRINHA, PRIMO, PRIMA, NAMORADO, AMIGO, VIZINHO, OUTRO
}

data class EmergencyContact(
    val uid: String = "",
    val name: String = "",
    val phones: List<String> = emptyList(),
    val relationship: Relationship = Relationship.OUTRO,
    val linkedUserId: String? = null,
    val ownerId: String = "",
)
