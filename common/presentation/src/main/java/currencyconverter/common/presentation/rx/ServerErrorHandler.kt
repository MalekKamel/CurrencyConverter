package currencyconverter.common.presentation.rx

import com.sha.rxrequester.Presentable
import com.sha.rxrequester.exception.handler.http.HttpExceptionHandler
import currencyconverter.common.presentation.R


class ServerErrorHandler : HttpExceptionHandler() {

    override fun supportedErrors(): List<Int> {
        return listOf(500)
    }

    override fun handle(throwable: Throwable, presentable: Presentable, errorCode: Int, errorBody: String) {
        presentable.showError(R.string.oops_something_went_wrong)
    }

}