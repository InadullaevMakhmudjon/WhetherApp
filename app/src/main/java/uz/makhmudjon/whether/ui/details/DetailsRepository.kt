package uz.makhmudjon.whether.ui.details

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.makhmudjon.whether.db.retrofit.model.CurrentHeader
import uz.makhmudjon.whether.db.retrofit.model.CurrentHistory
import uz.makhmudjon.whether.db.retrofit.model.FutureWhether
import uz.makhmudjon.whether.db.retrofit.service.WetherService

class DetailsRepository {

    val error = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>()

    var onFutureResponse:((List<FutureWhether>, CurrentHeader)->Unit)?=null

    var onHistoryResponse: ((MutableList<CurrentHistory>)->Unit)?=null

    fun loadFuture(server: WetherService, name:String,number:Int){
        isLoading.value = true
        server.getFutureWhether(name,number)
            .enqueue(object: Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    error.value = t.message
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    isLoading.value = false
                    if(response.isSuccessful){
                        if(response.body()!=null){
                            val data = response.body()
                            if(data!=null){
                                val futureList = mutableListOf<FutureWhether>()

                                val currentHeader = CurrentHeader(
                                    data.get("location").asJsonObject.get("region").asString,
                                    data.get("current").asJsonObject.get("condition").asJsonObject.get("text").asString,
                                    data.get("current").asJsonObject.get("temp_c").asInt,
                                    data.get("location").asJsonObject.get("localtime").asString)

                                data.get("forecast").asJsonObject.get("forecastday").asJsonArray.forEach {
                                    futureList.add(FutureWhether(
                                        it.asJsonObject.get("date").asString,
                                        it.asJsonObject.get("day").asJsonObject.get("condition").asJsonObject.get("icon").asString,
                                        it.asJsonObject.get("day").asJsonObject.get("maxtemp_c").asFloat.toInt().toString(),
                                        it.asJsonObject.get("day").asJsonObject.get("mintemp_c").asFloat.toInt().toString()
                                    ))
                                }

                                //Invokes Callback
                                onFutureResponse?.invoke(futureList,currentHeader)
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

    fun loadHistory(server: WetherService,country: String,date:String){
        isLoading.value = true
        server.getHistory(country,date).enqueue(object:Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                error.value = t.message
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                isLoading.value = false
                if(response.isSuccessful){
                    val data = response.body()
                    if(data!=null){
                        val todayHistory = mutableListOf<CurrentHistory>()

                        data.get("forecast").asJsonObject.get("forecastday").asJsonArray[0].asJsonObject.get("hour").asJsonArray.forEach {
                            todayHistory.add(
                                CurrentHistory(
                                it.asJsonObject.get("time").asString,
                                    it.asJsonObject.get("condition").asJsonObject.get("icon").asString,
                                    it.asJsonObject.get("temp_c").asFloat.toInt().toString()
                                )
                            )
                        }
                        onHistoryResponse?.invoke(todayHistory)

                    }else{
                        error.value = "Empty lis"
                    }
                }else{
                    error.value = response.message()
                }
            }

        })
    }
}