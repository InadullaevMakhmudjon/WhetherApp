package uz.makhmudjon.whether.util

import android.arch.persistence.room.Insert

interface GenericDAO<T> {

    @Insert
    fun insert(value:T)


}