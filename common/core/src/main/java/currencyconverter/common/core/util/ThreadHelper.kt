package currencyconverter.common.core.util

import android.os.Handler
import android.os.Looper

object ThreadHelper {

    fun runOnUiThread(runnable: () -> Unit) {
        Handler(Looper.getMainLooper()).post(runnable)
    }
}
