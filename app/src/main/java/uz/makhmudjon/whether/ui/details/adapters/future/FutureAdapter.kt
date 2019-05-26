package uz.makhmudjon.whether.ui.details.adapters.future

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.whether.R
import kotlinx.android.synthetic.main.item_future.view.*
import uz.makhmudjon.whether.db.retrofit.model.FutureWhether
import uz.makhmudjon.whether.util.toDayOfWeek
import java.text.SimpleDateFormat

class FutureAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<FViewHolder>() {

    var data = listOf<FutureWhether>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FViewHolder {
        return FViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_future,p0,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: FViewHolder, p1: Int) {
        p0.itemView.date.text = data[p1].date.toDayOfWeek()
        Glide.with(p0.itemView.context).load("https:${data[p1].image}").into(p0.itemView.image)
        p0.itemView.min.text = "${data[p1].minTemp}${0x00B0.toChar()}"
        p0.itemView.max.text = "${data[p1].minTemp}${0x00B0.toChar()}"
    }

    fun load(list:List<FutureWhether>){
        this.data = list
        notifyDataSetChanged()
    }
}