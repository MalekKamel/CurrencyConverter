package currencyconverter.common.data.currencyrate

import currencyconverter.common.data.model.CurrencyRateResponse
import currencyconverter.common.data.network.api.FixerAccessKey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRateApi {
    @GET("latest")
    fun latestRates(
            @Query("country") country: String = "US",
            @Query("access_key") accessKey: String = FixerAccessKey.KEY
    ): Single<CurrencyRateResponse>
}
