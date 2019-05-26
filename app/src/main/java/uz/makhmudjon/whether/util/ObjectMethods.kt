package uz.makhmudjon.whether.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.toDayOfWeek():String{
        val format = SimpleDateFormat("yyyy-MM-dd")
    return try{
        val dateObject = format.parse(this)
        val helper = SimpleDateFormat("EEEE")
        helper.format(dateObject)
    }catch (e:Exception){
        this
    }
}