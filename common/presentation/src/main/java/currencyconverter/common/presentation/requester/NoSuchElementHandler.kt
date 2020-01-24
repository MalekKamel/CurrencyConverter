package currencyconverter.common.presentation.requester

import com.sha.rxrequester.Presentable
import com.sha.rxrequester.exception.handler.throwable.ThrowableHandler
import currencyconverter.common.presentation.R
import java.util.*

class NoSuchElementHandler : ThrowableHandler<NoSuchElementException>() {

    override fun supportedErrors(): List<Class<out NoSuchElementException>> {
        return listOf(NoSuchElementException::class.java)
    }

    override fun handle(throwable: Throwable, presentable: Presentable) {
        presentable.showError(R.string.no_data_entered_yet)
    }
}
