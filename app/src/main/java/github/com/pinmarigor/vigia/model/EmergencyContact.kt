package github.com.pinmarigor.vigia.model

enum class Relationship {
    PAI, MÃE, IRMÃO, IRMÃ, MARIDO, ESPOSA, FILHO, FILHA, AVÔ, AVÓ, TIO, TIA, SOBRINHO, SOBRINHA, PRIMO, PRIMA, NAMORADO, AMIGO, VIZINHO, OUTRO
}

data class EmergencyContact(
    val id: String = "",
    val name: String = "",
    val phones: List<String> = emptyList(),
    val relationship: Relationship = Relationship.OUTRO,
    val linkedUserId: String? = null,
    val ownerId: String = "",
)
