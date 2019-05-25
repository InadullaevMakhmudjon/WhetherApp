package uz.makhmudjon.whether.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.widget.Toast
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.util.ObservableViewModel

class MainViewModel(val app: Application):ObservableViewModel(app) {
    val repository = MainRepository()

    val whether = repository.whether((app as App).whetherDAO)

    var image:((String)->Unit)?=null
    val temp = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val region = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun clicked() = repository.loadData((app as App).whetherSetvice,app.whetherDAO,"Uzbekistan")

    init {
        repository.onResponse = {
            temp.value = it.temp.toInt().toString()
            country.value = it.country
            region.value = it.region
            date.value = it.localtime.split(" ")[0]
            time.value = it.localtime.split(" ")[1]
            image?.invoke(it.icon)
        }

    }
}