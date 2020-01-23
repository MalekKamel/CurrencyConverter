package currencyconverter.common.data.network.api

import currencyconverter.common.data.model.CurrencyRateResponse
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
