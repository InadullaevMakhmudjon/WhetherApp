package uz.makhmudjon.whether.db.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

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