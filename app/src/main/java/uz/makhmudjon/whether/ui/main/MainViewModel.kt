package uz.makhmudjon.whether.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.widget.Toast
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.util.ObservableViewModel

class MainViewModel(val app: Application):ObservableViewModel(app) {
    val repository = MainRepository()

    @Bindable
    val editText = MutableLiveData<String>()

    fun clicked() = repository.loadData((app as App).whetherSetvice)

    init {
        repository.onResponse = {
            Toast.makeText(app,it,Toast.LENGTH_SHORT).show()
        }
    }
}