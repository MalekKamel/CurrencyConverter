package currencyconverter.common.presentation.ui

import currencyconverter.common.presentation.R


class StatusItem(var statusId: Int, var statusMessage: String) {

    fun getStatusColor(): Int {
        when (statusId) {
            ERROR, NO_INTERNET, SEND_FAILED -> return R.color.orange_light

            SUCCESS -> return R.color.green

            else -> return R.color.link
        }
    }

    companion object {

        val EMPTY = 0
        val ERROR = 1
        val SEND_FAILED = 2
        val NO_INTERNET = 3
        val NO_RESULTS = 4
        val SUCCESS = 5
    }
}
