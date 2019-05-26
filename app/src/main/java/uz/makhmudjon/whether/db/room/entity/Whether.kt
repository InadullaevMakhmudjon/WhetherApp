package uz.makhmudjon.whether.db.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Whether {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0

    var country = ""
    var region = ""
    var localtime=""
    var temp = 0
    var humidity = 0
    var wind = 0
    var icon = ""
}