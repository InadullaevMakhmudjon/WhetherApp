package uz.makhmudjon.whether.db.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import uz.makhmudjon.whether.db.room.dao.WhetherDAO
import uz.makhmudjon.whether.db.room.entity.Whether


@Database(entities = [Whether::class],version = 1,exportSchema = false )
abstract class AppDatabase: RoomDatabase(){
    abstract val whetherdao:WhetherDAO
}