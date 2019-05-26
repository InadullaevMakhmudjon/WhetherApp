package uz.makhmudjon.whether.ui.main.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.whether.R
import kotlinx.android.synthetic.main.item_country.view.*
import uz.makhmudjon.whether.db.room.entity.Whether


class WhetherAdapter: RecyclerView.Adapter<WhetherVH>() {

    var data = listOf<Whether>()

    var onClick:((String,String)->Unit)?= null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WhetherVH {
        return WhetherVH(LayoutInflater.from(p0.context).inflate(R.layout.item_country,p0,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: WhetherVH, p1: Int) {
        p0.itemView.humidity.text="${data[p1].humidity}%"
        p0.itemView.wind.text="${data[p1].wind}%"
        Glide.with(p0.itemView.context).load("https:${data[p1].icon}").into(p0.itemView.image)
        p0.itemView.degree.text = "${data[p1].temp}${0x00B0.toChar()}"
        p0.itemView.country.text = data[p1].country
        p0.itemView.region.text = data[p1].region
        p0.itemView.time.text = data[p1].localtime.split(" ")[1]

        p0.itemView.setOnClickListener {
            onClick?.invoke(data[p1].country,data[p1].localtime.split(" ")[0])
        }
    }

    fun load(data:List<Whether>){
        this.data = data
        notifyDataSetChanged()
    }
}