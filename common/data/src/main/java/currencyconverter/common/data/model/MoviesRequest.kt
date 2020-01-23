package currencyconverter.common.data.model

data class MoviesRequest (
        var search: String = "",
        var nextPage: Int = 1,
        var type: Type = Type.SEARCH
) {
    fun nextPage(nextPage: Int): MoviesRequest {
        this.nextPage = nextPage
        return this
    }

    enum class Type {
        SEARCH,
        POPULAR
    }
}

