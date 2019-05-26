package uz.makhmudjon.whether.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uz.makhmudjon.whether.db.room.entity.Whether
import uz.makhmudjon.whether.util.BasicDAO

@Dao
interface WhetherDAO:BasicDAO<Whether> {

    @Query("SELECT * FROM Whether")
    fun getAll():LiveData<List<Whether>>

    @Query("DELETE FROM Whether")
    fun drop()
}