package currencyconverter.common.data.model

import java.util.*

data class CurrencyRateResponse(
        val base: String,
        var rates: SortedMap<String, String>
)