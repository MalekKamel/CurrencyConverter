package currencyconverter.common.data

import currencyconverter.common.data.pref.SharedPref
import currencyconverter.common.data.domain.currencyrate.CurrencyRatesRepo

open class DataManager(
        val pref: SharedPref,
        val ratesRepo: CurrencyRatesRepo
)
