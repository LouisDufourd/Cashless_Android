package fr.plaglefleau.cashless.retrofit

import fr.plaglefleau.cashless.models.ListHistorique
import fr.plaglefleau.cashless.models.ListUtilisateur
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCashless {


    //GET request
    @GET("client_list")
    suspend fun getAllClients(): Response<ListUtilisateur>
    @GET("card_balance")
    suspend fun getSolde(@Query("codeNFC") codeNFC : String) : Response<Double>
    @GET("stand_historic")
    suspend fun getHistorique(@Query("idStand") idStand: Int) : Response<ListHistorique>

    //DELETE request
    @DELETE("delete_card")
    suspend fun deleteCard(@Query("idCarte") idCarte: Int) : Response<String>
    @DELETE("client_unsubscribe")
    suspend fun clientUnsubscribe(@Query("id") idClient: Int) : Response<String>
    @DELETE("stand_remove")
    suspend fun standRemove(@Query("idStand") idStand: Int) : Response<String>
    @DELETE("stock_remove")
    suspend fun stockRemove(@Query("idStand") idStand: Int, @Query("idArticle") idArticle:Int) : Response<String>
}