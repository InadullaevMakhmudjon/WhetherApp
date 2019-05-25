package uz.makhmudjon.whether.ui.main

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.JsonObject
import com.squareup.moshi.Json
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.makhmudjon.whether.db.retrofit.model.WhetherResponse
import uz.makhmudjon.whether.db.retrofit.service.WetherService

class MainRepository {

    val error = MutableLiveData<String>()

    var onResponse:((String)->Unit)?=null

    fun loadData(server:WetherService){
        server.getCurrentWether("Uzbekistan")
            .enqueue(object:Callback<JsonObject>{
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    error.value = t.message
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if(response.isSuccessful){
                        if(response.body()!=null){
                            val data = response.body()
                            if(data!=null) onResponse?.invoke(data.get("location").asJsonObject.get("country").asString)
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