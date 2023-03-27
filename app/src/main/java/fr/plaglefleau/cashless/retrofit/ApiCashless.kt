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
    suspend fun getAllClients(): Response<ListUtilisateur> //0-1
    @GET("card_balance")
    suspend fun getSolde(@Query("codeNFC") codeNFC : String) : Response<Double> //1-2
    @GET("stand_historic")
    suspend fun getHistorique(@Query("idStand") idStand: Int) : Response<ListHistorique> //2-3

    //DELETE request
    @DELETE("delete_card")
    suspend fun deleteCard(@Query("idCarte") idCarte: Int) : Response<String> //3-4
    @DELETE("client_unsubscribe")
    suspend fun clientUnsubscribe(@Query("id") idClient: Int) : Response<String> //4-5
    @DELETE("stand_remove")
    suspend fun standRemove(@Query("idStand") idStand: Int) : Response<String> //5-6
    @DELETE("stock_remove")
    suspend fun stockRemove(@Query("idStand") idStand: Int, @Query("idArticle") idArticle:Int) : Response<String> //6-7
    @DELETE("stock_remove_article")
    suspend fun stockRemoveArticle(@Query("idStand") idStand: Int, @Query("idArticle") idArticle: Int, @Query("amount") amount:Int) : Response<String> //7-8

    //POST request
    @POST("card_credit")
    suspend fun cardCredit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //8-9
    @POST("card_debit")
    suspend fun cardDebit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //9-10
    @POST("create_card")
    suspend fun createCard(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //10-11
    @POST("modify_card")
    suspend fun modifyCard(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //11-12
    @POST("card_connect")
    suspend fun cardConnect(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //12-13
    @POST("client_edit")
    suspend fun clientEdit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //13-14
    @POST("client_register")
    suspend fun clientRegister(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //14-15
    @POST("client_connect")
    suspend fun clientConnect(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //15-16
    @POST("stand_edit")
    suspend fun standEdit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //16-17
    @POST("stock_add_article")
    suspend fun stockAddArticle(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //17-18
    @POST("stock_add")
    suspend fun stockAdd(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //18-19
    @POST("stock_edit")
    suspend fun stockEdit(@Query("codeNFC") codeNFC: String, @Query("amount") amount: Double) : Response<String> //19-20

}