package currencyconverter.common.data

import currencyconverter.common.data.pref.SharedPref
import currencyconverter.common.data.restaurant.CurrencyRatesRepo

open class DataManager(
        val pref: SharedPref,
        val ratesRepo: CurrencyRatesRepo
)
