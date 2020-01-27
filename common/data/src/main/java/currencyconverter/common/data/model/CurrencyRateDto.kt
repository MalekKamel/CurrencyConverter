package currencyconverter.common.data.model

import com.sha.modelmapper.Mapper

class CurrencyRateMapper: Mapper<Map.Entry<String, String>, CurrencyRate> {
        override fun map(input: Map.Entry<String, String>): CurrencyRate {
                return CurrencyRate(
                        currency = input.key,
                        rate = input.value.toDouble()
                )
        }
}
