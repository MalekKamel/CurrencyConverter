package currencyconverter.common.presentation.requester

import com.sha.rxrequester.Presentable
import com.sha.rxrequester.exception.handler.throwable.ThrowableHandler
import currencyconverter.common.presentation.R

class OutOfMemoryErrorHandler : ThrowableHandler<OutOfMemoryError>() {

    override fun supportedErrors(): List<Class<OutOfMemoryError>> = listOf(OutOfMemoryError::class.java)

    override fun handle(throwable: Throwable, presentable: Presentable) {
        presentable.showError(R.string.no_memory_free_up_space)
    }
}
