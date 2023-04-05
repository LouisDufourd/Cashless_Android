package fr.plaglefleau.cashless.retrofit

import fr.plaglefleau.cashless.holder.MyAdapterClient
import fr.plaglefleau.cashless.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiCashless {


    //GET request
    @GET("client_list")
    suspend fun getAllClients(): Response<ListUtilisateur> //0
    @GET("card_balance/{codeNFC}/")
    suspend fun getSolde(@Path("codeNFC") codeNFC : String) : Response<Double> //1
    @GET("stand_historic/{idStand}/")
    suspend fun getHistorique(@Path("idStand") idStand: Int) : Response<ListHistorique> //2
    @GET("client_connect/{user}/{password}/")
    suspend fun clientConnect(@Path("user") user: String, @Path("password") password: String) : Response<String> //15-16

    //DELETE request
    @HTTP(method = "DELETE", path = "delete_card", hasBody = true)
    suspend fun deleteCard(@Body carte: Carte) : Response<String> //3
    @HTTP(method = "DELETE", path = "client_unsubscribe", hasBody = true)
    suspend fun clientUnsubscribe(@Body utilisateur: Utilisateur) : Response<String> //4
    @HTTP(method = "DELETE", path = "stand_remove", hasBody = true)
    suspend fun standRemove(@Body stand: Stand) : Response<String> //5
    @HTTP(method = "DELETE", path = "stock_remove", hasBody = true)
    suspend fun stockRemove(@Body stock: Stock) : Response<String> //6

    //PUT
    @PUT("stock_remove_article")
    suspend fun stockRemoveArticle(@Body stock: Stock) : Response<String> //7
    @PUT("card_credit")
    suspend fun cardCredit(@Body carte: Carte) : Response<String> //8-9
    @PUT("stock_edit")
    suspend fun stockEdit(@Body stock: Stock) : Response<String> //19-20
    @PUT("stock_add_article")
    suspend fun stockAddArticle(@Body stock: Stock) : Response<String> //17-18
    @PUT("card_debit")
    suspend fun cardDebit(@Body carte: Carte) : Response<String> //9-10
    @PUT("modify_card")
    suspend fun modifyCard(@Body carte: Carte) : Response<String> //11-12
    @PUT("card_connect")
    suspend fun cardConnect(@Body carte: Carte) : Response<String> //12-13
    @PUT("client_edit")
    suspend fun clientEdit(@Body utilisateur: Utilisateur) : Response<String> //13-14

    //POST
    @POST("create_card")
    suspend fun createCard(@Body carte: Carte) : Response<String> //10-11
    @POST("client_register")
    suspend fun clientRegister(@Body utilisateur: Utilisateur) : Response<String> //14-15
    @POST("stock_add")
    suspend fun stockAdd(@Body stock: Stock) : Response<String> //18-19
    @POST("stand_add")
    suspend fun standAdd(@Body stand: Stand) : Response<String>


}