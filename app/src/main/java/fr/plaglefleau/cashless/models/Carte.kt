package fr.plaglefleau.cashless.models

data class Carte(
    val argent: Double,
    val codeNFC: String,
    val id: Int,
    val pin: Int
) {
    override fun toString(): String {
        return "    argent:$argent\n" +
               "    codeNFC:$codeNFC\n" +
               "    id:$id\n" +
               "    pin:$pin"
    }
}