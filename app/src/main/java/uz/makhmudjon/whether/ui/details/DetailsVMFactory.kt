package uz.makhmudjon.whether.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.makhmudjon.whether.App

class DetailsVMFactory(val app:App): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(app) as T
    }
}