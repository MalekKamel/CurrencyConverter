package currencyconverter.common.data.restaurant

import currencyconverter.common.data.base.BaseRepo
import currencyconverter.common.data.model.CurrencyRateResponse
import io.reactivex.Single

class CurrencyRatesRepo(private val currencyRateDataSrc: CurrencyRateDataSrc): BaseRepo() {

    fun latest(): Single<CurrencyRateResponse> {
        return currencyRateDataSrc.latestRates()
    }


}
