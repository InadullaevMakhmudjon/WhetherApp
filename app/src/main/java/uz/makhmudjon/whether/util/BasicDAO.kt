package uz.makhmudjon.whether.util

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BasicDAO<T> {

    @Insert
    fun insert(value:T)

    @Update
    fun update(value:T)

    @Delete
    fun delete(value:T)

}