package currencyconverter.common.data.network.api

interface ApiErrorContract {
    val success: Boolean
    val error: ApiError?
}

data class ApiError(
        val code: Int,
        val type: String,
        val info: String
)
