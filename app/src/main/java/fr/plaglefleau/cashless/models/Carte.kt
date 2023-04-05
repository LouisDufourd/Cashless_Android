package fr.plaglefleau.cashless.models

import com.google.gson.Gson

data class Carte(var id:Int?, val pin:Int?, val argent:Double?, val codeNFC:String?) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
