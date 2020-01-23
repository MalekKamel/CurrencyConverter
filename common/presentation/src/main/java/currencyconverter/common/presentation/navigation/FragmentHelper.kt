package currencyconverter.common.presentation.navigation

import androidx.fragment.app.Fragment

fun <T: Fragment> fragmentFrom(addressableFragment: AddressableFragment): T {
    return Class.forName(addressableFragment.className).newInstance() as T
}

interface AddressableFragment {
    /**
     * The fragment class name.
     */
    val className: String
}

object Fragments {

    object Splash: AddressableFragment {
        override val className: String = "$FEATURE_PACKAGE_NAME.splash.SplashFrag"
    }

    object Home: AddressableFragment {
        override val className: String = "$FEATURE_PACKAGE_NAME.home.HomeFrag"
    }

    object RateCalculator: AddressableFragment {
        const val CURRENCY_NAME = "CURRENCY_NAME"
        const val CURRENCY_RATE = "CURRENCY_RATE"
        override val className: String = "$FEATURE_PACKAGE_NAME.ratecalc.RateCalculatorFrag"
    }
}