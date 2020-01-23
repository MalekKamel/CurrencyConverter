package currencyconverter.common.core.util

import java.lang.Exception

/**
 * Created by Sha on 10/15/17.
 */

object CrashlyticsUtil {

    fun logAndPrint(throwable: Throwable) {
        // In production we must uncomment
        throwable.printStackTrace()
//        Crashlytics.getInstance().core.logException(throwable)
    }

    fun logAndPrint(msg: String) {
        // In production we must uncomment
//        Crashlytics.getInstance().core.logException(Throwable(msg))
    }
}

fun Exception.reportAndPrint() {
    CrashlyticsUtil.logAndPrint(this)
}