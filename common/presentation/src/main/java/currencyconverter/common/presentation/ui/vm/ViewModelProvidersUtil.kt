package currencyconverter.common.presentation.ui.vm

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders


import org.koin.java.KoinJavaComponent

object ViewModelProvidersUtil {

    fun <VM : BaseViewModel> get(
            clazz: Class<VM>,
            activity: FragmentActivity
    ): VM {
        val viewModel = KoinJavaComponent.get(clazz)
        return ViewModelProviders.of(
                activity,
                ViewModelProviderFactory(viewModel)
        ).get(clazz)
    }
}
