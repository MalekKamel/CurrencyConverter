package currencyconverter.common.data.domain.currencyrate

import currencyconverter.common.data.model.CurrencyRateResponse
import io.reactivex.Single

class CurrencyRatesRepo(private val currencyRateDataSrc: CurrencyRateDataSrc) {

    fun latest(): Single<CurrencyRateResponse> {
        return currencyRateDataSrc.latestRates()
    }


}
