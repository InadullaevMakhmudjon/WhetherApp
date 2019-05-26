package uz.makhmudjon.whether.ui.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import uz.makhmudjon.whether.App

class DetailsVMFactory(val app:App): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(app) as T
    }
}