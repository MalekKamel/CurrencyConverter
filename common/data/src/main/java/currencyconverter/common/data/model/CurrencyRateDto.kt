package currencyconverter.common.data.model

import com.sha.modelmapper.Mapper

data class CurrencyRateDto(
        var currency: String,
        var rate: String
)

class CurrencyRateMapper: Mapper<Map.Entry<String, String>, CurrencyRate> {
        override fun map(input: Map.Entry<String, String>): CurrencyRate {
                return CurrencyRate(
                        currency = input.key,
                        rate = input.value.toDouble()
                )
        }
}
