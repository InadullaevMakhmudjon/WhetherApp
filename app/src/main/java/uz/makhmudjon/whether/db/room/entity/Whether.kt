package uz.makhmudjon.whether.db.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Whether(
    var country: String,
    var region: String,
    var localtime: String,
    var temp: Float,
    var humidity: Int,
    var wind: Int,
    var icon: String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int=1

}