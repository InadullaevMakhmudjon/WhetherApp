package uz.makhmudjon.whether.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.makhmudjon.whether.db.room.dao.WhetherDAO
import uz.makhmudjon.whether.db.room.entity.Whether


@Database(entities = [Whether::class],version = 3,exportSchema = false )
abstract class AppDatabase: RoomDatabase(){
    abstract val whetherdao:WhetherDAO
}