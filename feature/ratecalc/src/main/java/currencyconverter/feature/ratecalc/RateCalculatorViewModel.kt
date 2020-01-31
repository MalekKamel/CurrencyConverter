package currencyconverter.feature.ratecalc

import currencyconverter.common.data.DataManager
import currencyconverter.common.presentation.ui.vm.BaseViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rateCalculatorModule = module {
    viewModel { RateCalculatorViewModel(get()) }
}

class RateCalculatorViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun calculateRate(amount: Double, base: Double) = amount * base
}
