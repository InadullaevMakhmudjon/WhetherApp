package uz.makhmudjon.whether.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.JsonObject
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.makhmudjon.whether.db.retrofit.model.WhetherResponse
import uz.makhmudjon.whether.db.retrofit.service.WetherService
import uz.makhmudjon.whether.db.room.dao.WhetherDAO
import uz.makhmudjon.whether.db.room.entity.Whether

class MainRepository {

    val error = MutableLiveData<String>()

    var onResponse:((Whether)->Unit)?=null

    fun whether(dao:WhetherDAO):LiveData<List<Whether>> = dao.getAll()

    fun loadData(server:WetherService,database:WhetherDAO,name:String){
        server.getCurrentWether(name)
            .enqueue(object:Callback<JsonObject>{
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    error.value = t.message
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if(response.isSuccessful){
                        if(response.body()!=null){
                            val data = response.body()
                            if(data!=null){
                                val result = Whether()

                                result.country = data.get("location").asJsonObject.get("country").asString
                                result.region = data.get("location").asJsonObject.get("region").asString
                                result.localtime = data.get("location").asJsonObject.get("localtime").asString
                                result.temp = data.get("current").asJsonObject.get("temp_c").asDouble.toInt()
                                result.humidity = data.get("current").asJsonObject.get("humidity").asInt
                                result.wind = data.get("current").asJsonObject.get("wind_degree").asInt
                                result.icon = data.get("current").asJsonObject.get("condition").asJsonObject.get("icon").asString

                                database.insert(result)

                                if(result.country == "Uzbekistan")
                                onResponse?.invoke(result)
                            }
                            else Log.e("ThisIsMyName","failed")
                        }else{
                            Log.e("ThisIsMyName","EmptyBody")
                        }
                    }else{
                        error.value = response.message()
                    }
                }

            })
    }
}