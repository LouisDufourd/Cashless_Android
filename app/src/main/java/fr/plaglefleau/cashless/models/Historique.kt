package fr.plaglefleau.cashless.models

data class Historique(
    val argent: Double,
    val carte: Carte,
    val horodatage: String,
    val idHistorique: Int,
    val stand: Stand
) {
    override fun toString(): String {
        return "argent:$argent\n" +
               "carte:\n$carte\n" +
               "horodatage:$horodatage\n" +
               "idHistorique=$idHistorique\n" +
               "stand:\n$stand"
    }
}