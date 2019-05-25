package uz.makhmudjon.whether.db.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import uz.makhmudjon.whether.db.room.entity.Whether
import uz.makhmudjon.whether.util.BasicDAO

@Dao
interface WhetherDAO:BasicDAO<Whether> {

    @Query("SELECT * FROM Whether")
    fun getAll():LiveData<List<Whether>>

    @Query("DELETE FROM Whether")
    fun drop()
}