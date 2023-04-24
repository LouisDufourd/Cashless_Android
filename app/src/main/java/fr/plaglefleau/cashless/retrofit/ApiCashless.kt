package fr.plaglefleau.cashless.retrofit

import fr.plaglefleau.cashless.models.*
import fr.plaglefleau.cashless.models.response.carte.*
import fr.plaglefleau.cashless.models.response.stand.StandAddResponse
import fr.plaglefleau.cashless.models.response.stand.StandEditResponse
import fr.plaglefleau.cashless.models.response.stand.StandHistoriqueResponse
import fr.plaglefleau.cashless.models.response.stand.StandRemoveResponse
import fr.plaglefleau.cashless.models.response.stock.*
import fr.plaglefleau.cashless.models.response.utilisateur.*
import retrofit2.Response
import retrofit2.http.*

interface ApiCashless {


    //GET request
   @GET("card_balance/{codeNFC}")
    suspend fun getSolde(@Path("codeNFC") codeNFC : String) : Response<CardBalanceResponse> //0
    @GET("stand_historic/{idStand}")
    suspend fun getHistorique(@Path("idStand") idStand: Int) : Response<StandHistoriqueResponse> //1
    @GET("client_connect/{user}/{password}")
    suspend fun clientConnect(@Path("user") user: String, @Path("password") password: String) : Response<ClientConnectResponse> //11

    //DELETE request
    @HTTP(method = "DELETE", path = "delete_card", hasBody = true)
    suspend fun deleteCard(@Body carte: Carte) : Response<DeleteCardResponse> //2
    @HTTP(method = "DELETE", path = "client_unsubscribe", hasBody = true)
    suspend fun clientUnsubscribe(@Body utilisateur: Utilisateur) : Response<ClientUnsubscribeResponse> //3
    @HTTP(method = "DELETE", path = "stand_remove", hasBody = true)
    suspend fun standRemove(@Body stand: Stand) : Response<StandRemoveResponse> //4
    @HTTP(method = "DELETE", path = "stock_remove", hasBody = true)
    suspend fun stockRemove(@Body stock: Stock) : Response<StockRemoveResponse> //5

    //PUT
    @PUT("stock_remove_article")
    suspend fun stockRemoveArticle(@Body stock: Stock) : Response<StockRemoveArticleResponse> //6
    @PUT("card_credit")
    suspend fun cardCredit(@Body carte: Carte) : Response<CardCreditResponse> //7
    @PUT("stock_edit")
    suspend fun stockEdit(@Body stock: Stock) : Response<StockEditResponse> //12
    @PUT("stock_add_article")
    suspend fun stockAddArticle(@Body stock: Stock) : Response<StockAddArticleResponse> //13
    @PUT("card_debit")
    suspend fun cardDebit(@Body carte: Carte) : Response<CardDebitResponse> //8
    @PUT("modify_card")
    suspend fun modifyCard(@Body carte: Carte) : Response<ModifyCardResponse> //9
    @PUT("card_connect")
    suspend fun cardConnect(@Body utilisateur: Utilisateur) : Response<CardConnectResponse> //14
    @PUT("client_edit")
    suspend fun clientEdit(@Body utilisateur: Utilisateur) : Response<ClientEditResponse> //15
    @PUT("stand_edit")
    suspend fun standEdit(@Body stand: Stand) : Response<StandEditResponse> //19

    //POST
    @POST("create_card")
    suspend fun createCard(@Body carte: Carte) : Response<CreateCardResponse> //10
    @POST("client_register")
    suspend fun clientRegister(@Body utilisateur: Utilisateur) : Response<ClientRegisterResponse> //16
    @POST("stock_add")
    suspend fun stockAdd(@Body stock: Stock) : Response<StockAddResponse> //17
    @POST("stand_add")
    suspend fun standAdd(@Body stand: Stand) : Response<StandAddResponse> //18


}