package currencyconverter.feature.home

import com.sha.modelmapper.ListMapper
import currencyconverter.common.data.DataManager
import currencyconverter.common.data.model.CurrencyRate
import currencyconverter.common.data.model.CurrencyRateMapper
import currencyconverter.common.presentation.ui.vm.BaseViewModel
import io.reactivex.Flowable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}

class HomeViewModel(dataManager: DataManager) : BaseViewModel(dataManager) {

    fun latestRates(): Flowable<List<CurrencyRate>> {
       return requester.request { dm.ratesRepo.latest().toFlowable() }
                .map { ListMapper(CurrencyRateMapper()).map(it.rates.entries.toList()) }
    }

}
