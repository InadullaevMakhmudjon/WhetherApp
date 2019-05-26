package uz.makhmudjon.whether.ui.details

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.whether.R
import com.example.whether.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.ui.details.adapters.future.FutureAdapter
import uz.makhmudjon.whether.ui.details.adapters.today.TodayAdapter
import uz.makhmudjon.whether.util.toDayOfWeek

class DetailsActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProviders.of(this,DetailsVMFactory(this.application as App))[DetailsViewModel::class.java]
        binding.viewModel = viewModel
        val country = intent.getStringExtra("name")
        val date = intent.getStringExtra("date")
        val futureAdapter = FutureAdapter()
        val todayAdapter = TodayAdapter()

        fullDetails.adapter = todayAdapter
        futurecontainer.adapter = futureAdapter

        viewModel.onFutureLoaded = {futureData,currentHeader ->
            futureAdapter.load(futureData)
            viewModel.countryName.value = currentHeader.country
            viewModel.status.value = currentHeader.status
            viewModel.description.value = "${date.toDayOfWeek()} Today"
            viewModel.temperature.value = "${currentHeader.temp}${0x00B0.toChar()}"

        }

        viewModel.onHistoryLoaded = { historyData->
            todayAdapter.load(historyData)
        }

        viewModel.loadFuture(country,10)
        viewModel.loadHistory(country,date)

        supportActionBar?.title = "Details"
    }
}
