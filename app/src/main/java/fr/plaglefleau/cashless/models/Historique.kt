package fr.plaglefleau.cashless.models

import com.google.gson.Gson

data class Historique(val idHistorique: Int, val horodatage:String, val carte: Carte, val stand: Stand, val argent:Double) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
