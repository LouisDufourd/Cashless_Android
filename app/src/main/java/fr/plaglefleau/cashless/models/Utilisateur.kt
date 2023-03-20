package fr.plaglefleau.cashless.models

data class Utilisateur(
    val carte: Carte?,
    val id: Int,
    val password: String,
    val user: String
) {
    override fun toString(): String {
        if(carte != null) {
            return "Id du client : $id\n" +
                    "Nom d'utilisateur : $user\n" +
                    "Id de la carte : ${carte.id}\n" +
                    "Pin de la carte : ${carte.pin}\n" +
                    "Code NFC de la carte : ${carte.codeNFC}\n" +
                    "Solde de la carte : ${carte.argent}"
        } else {
            return "Id du client : $id\n" +
                    "Nom d'utilisateur : $user"
        }
    }
}