package fr.plaglefleau.cashless.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    val api = Retrofit.Builder()
        .baseUrl("http://10.0.0.111:8080/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiCashless::class.java)
}