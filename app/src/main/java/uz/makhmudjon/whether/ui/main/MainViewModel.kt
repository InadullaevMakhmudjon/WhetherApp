package uz.makhmudjon.whether.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.databinding.Bindable
import android.widget.Toast
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.util.ObservableViewModel

class MainViewModel(val app: Application):ObservableViewModel(app) {
    val repository = MainRepository()

    val whether = repository.whether((app as App).database.whetherdao)

    val isLoading = repository.isloading

    var image:((String)->Unit)?=null
    val temp = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val region = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun load(){
        repository.loadData((app as App).whetherSetvice,app.database.whetherdao,"Uzbekistan")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Paris")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"China")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Mongolia")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"India")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Russia")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Iran")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Turkey")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Ukraine")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Poland")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Germany")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Italy")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Spain")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Japan")
        repository.loadData((app).whetherSetvice,app.database.whetherdao,"Finland")

    }

    init {
        repository.onResponse = {
            temp.value = "${it.temp}${0x00B0.toChar()}"
            country.value = it.country
            region.value = it.region
            date.value = it.localtime.split(" ")[0]
            time.value = it.localtime.split(" ")[1]
            image?.invoke(it.icon)
        }
    }
}