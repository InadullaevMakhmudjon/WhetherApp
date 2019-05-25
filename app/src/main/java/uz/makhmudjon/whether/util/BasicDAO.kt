package uz.makhmudjon.whether.util

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

interface BasicDAO<T> {

    @Insert
    fun insert(value:T)

    @Update
    fun update(value:T)

    @Delete
    fun delete(value:T)

}