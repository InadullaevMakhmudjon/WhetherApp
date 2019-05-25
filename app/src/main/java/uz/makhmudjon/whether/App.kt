package uz.makhmudjon.whether

import android.app.Application
import uz.makhmudjon.whether.di.AppModule

class App: Application() {

    private val appmodule = AppModule()

    val whetherSetvice = appmodule.retrofit.wetherService

    override fun onCreate() {
        super.onCreate()
    }

}