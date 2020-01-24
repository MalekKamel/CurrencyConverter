package currencyconverter.common.presentation.requester

import com.sha.rxrequester.Presentable
import currencyconverter.common.data.network.api.ApiErrorContract
import io.reactivex.Flowable
import com.sha.rxrequester.Request
import com.sha.rxrequester.RequestOptions
import com.sha.rxrequester.RxRequester

class AppRequester(presentable: Presentable) {
    private val requester: RxRequester by lazy {
        RxRequester.create(presentable) {
            httpHandlers = listOf(ServerErrorHandler())
            throwableHandlers = listOf(IoExceptionHandler(), NoSuchElementHandler(), OutOfMemoryErrorHandler())
            serverErrorContract = ErrorContract::class.java
        }
    }

    fun <T: ApiErrorContract> request(
            requestOptions: RequestOptions = RequestOptions.defaultOptions(),
            request: Request<T>
    ): Flowable<T> {
        // Here we map the returned API error, as the error is returned with code 200 OK!
        // If success is false, we just return the error of the API.
        val mapped = request().flatMap {response ->
            if (!response.success)
                return@flatMap Flowable.error<T>(Throwable(response.error?.info ?: "Error"))
            return@flatMap Flowable.just(response)
        }
        return requester.request(requestOptions) { mapped }

    }
}
