package uz.makhmudjon.whether.ui.details

import androidx.lifecycle.MutableLiveData
import uz.makhmudjon.whether.App
import uz.makhmudjon.whether.db.retrofit.model.CurrentHeader
import uz.makhmudjon.whether.db.retrofit.model.CurrentHistory
import uz.makhmudjon.whether.db.retrofit.model.FutureWhether
import uz.makhmudjon.whether.util.ObservableViewModel

class DetailsViewModel(val app:App):ObservableViewModel(app) {

    val repository = DetailsRepository()

    var onFutureLoaded:((List<FutureWhether>, CurrentHeader)->Unit)?=null

    var onHistoryLoaded: ((MutableList<CurrentHistory>)->Unit)?=null

    val countryName = MutableLiveData<String>()

    val status = MutableLiveData<String>()

    val temperature = MutableLiveData<String>()

    val description = MutableLiveData<String>()

    fun loadFuture(name:String,number:Int) = repository.loadFuture(app.whetherSetvice,name,number)

    fun loadHistory(name:String,date:String) = repository.loadHistory(app.whetherSetvice,name,date)

    init {
        repository.onFutureResponse = { data,header->
            onFutureLoaded?.invoke(data,header)
        }

        repository.onHistoryResponse = {data->
            onHistoryLoaded?.invoke(data)
        }
    }
}