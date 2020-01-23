package currencyconverter.feature.splash

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import currencyconverter.common.presentation.ui.vm.BaseViewModel
import currencyconverter.common.data.DataManager

/**
 * Created by Sha on 9/15/17.
 */

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

class SplashViewModel(dataManager: DataManager) : BaseViewModel(dataManager)