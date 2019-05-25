package uz.makhmudjon.whether

import android.app.Application
import uz.makhmudjon.whether.db.retrofit.service.WetherService
import uz.makhmudjon.whether.db.room.dao.WhetherDAO
import uz.makhmudjon.whether.di.AppModule

class App: Application() {

    lateinit var whetherSetvice:WetherService

    lateinit var whetherDAO:WhetherDAO

    override fun onCreate() {
        super.onCreate()
        val appmodule = AppModule(this)
        whetherSetvice = appmodule.retrofit.wetherService
        whetherDAO = appmodule.database.whetherdao
    }

}