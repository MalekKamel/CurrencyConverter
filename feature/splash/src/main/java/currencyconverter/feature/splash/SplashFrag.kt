package currencyconverter.feature.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import com.sha.kamel.navigator.FragmentNavigator
import org.koin.android.viewmodel.ext.android.viewModel
import currencyconverter.common.presentation.navigation.Fragments
import currencyconverter.common.presentation.navigation.fragmentFrom
import currencyconverter.common.presentation.ui.frag.BaseFrag
import currencyconverter.feature.splash.di.injectFeature

class SplashFrag : BaseFrag<SplashViewModel>() {

    override var layoutId: Int = R.layout.frag_splash

    override val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
    }

    override fun doOnViewCreated() {
        Runnable {
           FragmentNavigator(activity as FragmentActivity)
                   .add(fragmentFrom(Fragments.Home), false)
       }.apply { Handler().postDelayed(this, 2000)  }
    }

}
