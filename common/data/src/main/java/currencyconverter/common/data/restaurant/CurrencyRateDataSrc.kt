package currencyconverter.common.data.restaurant

import currencyconverter.common.data.model.CurrencyRateResponse
import currencyconverter.common.data.network.api.CurrencyRateApi
import io.reactivex.Single

class CurrencyRateDataSrc(private val currencyRateApi: CurrencyRateApi) {

    fun latestRates(): Single<CurrencyRateResponse> {
        return currencyRateApi.latestRates()
    }

}