package uz.makhmudjon.whether.db.retrofit.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Makhmudjon: Rest API methods to get current whether
interface WetherService {

    val jj:JsonObject

    @GET("current.json?key= b729a1cd68a34d6ba00204524192305")
    fun getCurrentWether(@Query("q")country:String):Call<JsonObject>

}