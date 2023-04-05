package fr.plaglefleau.cashless.models

import com.google.gson.Gson

data class Utilisateur(val id:Int?, val carte: Carte?, val user:String?, val password:String?) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
