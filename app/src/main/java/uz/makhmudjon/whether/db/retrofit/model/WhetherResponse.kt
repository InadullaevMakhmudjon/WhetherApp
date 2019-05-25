package uz.makhmudjon.whether.db.retrofit.model

data class Location(
    var name:String,
    val region:String,
    val country:String,
    val localtime:String
)

data class Current(
    val last_updated:String,
    val condition: Condition,
    val wind_mph:Float,
    val wind_kph:Float,
    val wind_degree:Int,
    val pressure_mb:Int,
    val pressure_in:Float,
    val precip_mm:Float,
    val precip_in:Float,
    val humidity:Int,
    val cloud:Int,
    val feelslike_c:Int,
    val feelslike_f:Float

)

data class Condition(
    val text:String,
    val icon:String,
    val code:Int
)

data class WhetherResponse(
    var location:Location,
    var current: Current
)
