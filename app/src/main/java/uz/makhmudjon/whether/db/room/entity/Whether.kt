package uz.makhmudjon.whether.db.room.entity

import android.arch.persistence.room.Entity

@Entity
data class Whether(
    val country:String,
    val region:String,
    val localtime:String,

    val temp:Float,
    val humidity:Int,
    val wind:Int,
    val icon:String
)