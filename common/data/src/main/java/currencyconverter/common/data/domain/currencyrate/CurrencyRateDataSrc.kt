package currencyconverter.common.data.domain.currencyrate

import currencyconverter.common.data.model.CurrencyRateResponse
import io.reactivex.Single

class CurrencyRateDataSrc(private val currencyRateApi: CurrencyRateApi) {

    fun latestRates(): Single<CurrencyRateResponse> {
        return currencyRateApi.latestRates()
    }

}