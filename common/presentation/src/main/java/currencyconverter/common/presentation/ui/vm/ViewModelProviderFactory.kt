package currencyconverter.common.presentation.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A provider factory that persists ViewModels[ViewModel].
 * Used if the viewmodel has a parameterized constructor.
 */
class ViewModelProviderFactory<V>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}
