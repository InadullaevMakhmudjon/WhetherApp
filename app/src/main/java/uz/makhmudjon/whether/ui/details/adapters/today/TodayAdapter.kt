package uz.makhmudjon.whether.ui.details.adapters.today

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.whether.R
import kotlinx.android.synthetic.main.item_today.view.*
import uz.makhmudjon.whether.db.retrofit.model.CurrentHistory

class TodayAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<TViewHolder>() {

    var data = listOf<CurrentHistory>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TViewHolder {
            return TViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_today,p0,false))
    }

    override fun getItemCount(): Int {
           return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: TViewHolder, p1: Int) {
            p0.itemView.time.text = data[p1].time.split(" ")[1]
            Glide.with(p0.itemView.context).load("https:${data[p1].image}").into(p0.itemView.image)
            p0.itemView.temperature.text = "${data[p1].temp}${0x00B0.toChar()}"
    }

    fun load(list:List<CurrentHistory>){
        this.data = list
        notifyDataSetChanged()
    }
}