package uz.makhmudjon.whether.di

import androidx.room.Room
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.db.retrofit.AppRetrofit
import uz.makhmudjon.whether.db.room.AppDatabase

class AppModule(val app: App) {
    val retrofit: AppRetrofit = AppRetrofit()
    var database:AppDatabase = Room.databaseBuilder(app,AppDatabase::class.java,"whether.db").allowMainThreadQueries().build()
}