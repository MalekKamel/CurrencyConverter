package currencyconverter.common.data.model

import currencyconverter.common.data.network.api.ApiError
import currencyconverter.common.data.network.api.ApiErrorContract
import java.util.*

data class CurrencyRateResponse(
        val base: String,
        var rates: SortedMap<String, String>,
        override val success: Boolean,
        override val error: ApiError?
): ApiErrorContract