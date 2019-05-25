package uz.makhmudjon.whether.db.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.makhmudjon.whether.db.retrofit.service.WetherService

class AppRetrofit{

    var wetherService:WetherService

    //Creating instance//Create one time instance and web service
    init {
        val instance = Retrofit.Builder()
            .baseUrl("http://api.apixu.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        wetherService = instance.create(WetherService::class.java)
    }

}