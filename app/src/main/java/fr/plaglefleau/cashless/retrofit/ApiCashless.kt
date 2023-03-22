package fr.plaglefleau.cashless.retrofit

import fr.plaglefleau.cashless.models.ListHistorique
import fr.plaglefleau.cashless.models.ListUtilisateur
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiCashless {


    //GET request
    @GET("client_list")
    suspend fun getAllClients(): Response<ListUtilisateur> //0
    @GET("card_balance")
    suspend fun getSolde(@Query("codeNFC") codeNFC : String) : Response<Double> //1
    @GET("stand_historic")
    suspend fun getHistorique(@Query("idStand") idStand: Int) : Response<ListHistorique> //2

    //DELETE request
    @DELETE("delete_card")
    suspend fun deleteCard(@Query("idCarte") idCarte: Int) : Response<String> //3
    @DELETE("client_unsubscribe")
    suspend fun clientUnsubscribe(@Query("id") idClient: Int) : Response<String> //4
    @DELETE("stand_remove")
    suspend fun standRemove(@Query("idStand") idStand: Int) : Response<String> //5
    @DELETE("stock_remove")
    suspend fun stockRemove(@Query("idStand") idStand: Int, @Query("idArticle") idArticle:Int) : Response<String> //6
    @DELETE("stock_remove_article")
    suspend fun stockRemoveArticle(@Query("idStand") idStand: Int, @Query("idArticle") idArticle: Int, @Query("amount") amount:Int) : Response<String> //7

    //POST
    @POST("card_credit")
    suspend fun cardCredit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //8

}