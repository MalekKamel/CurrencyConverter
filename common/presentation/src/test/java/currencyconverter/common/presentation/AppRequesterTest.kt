package currencyconverter.common.presentation


import com.sha.rxrequester.Presentable
import com.sha.rxrequester.RxRequester
import com.sha.rxrequester.TestSchedulerProvider
import currencyconverter.common.data.network.api.ApiError
import currencyconverter.common.data.network.api.ApiErrorContract
import currencyconverter.common.presentation.requester.AppRequester
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test


class AppRequesterTest : BaseUnitTest() {
    private lateinit var requester: AppRequester
    private lateinit var presentable: FakePresentable

    @Before
    fun setup() {
        presentable = FakePresentable()
        requester = AppRequester(presentable)
        RxRequester.defaultSchedulerProvider = TestSchedulerProvider
    }

    @Test
    fun `request() should return an error if ApiErrorContract#success = false`() {
        // requester will call onHandleErrorFailed when it fails handling the error
        // we threw when success = false
        val error = FakeApiErrorContract()
        requester.request { Flowable.just(error) }
                .test()
                .assertNoErrors()
        assert(presentable.throwable?.message == "ApiError")
    }

}

class FakeApiErrorContract: ApiErrorContract {
    override val success: Boolean  = false
    override val error: ApiError?
        get() = ApiError(
                code = 0,
                type = "error",
                info = "ApiError"
        )
}

class FakePresentable: Presentable {
    var throwable: Throwable? = null
    override fun hideLoading() {}
    override fun onHandleErrorFailed(throwable: Throwable) {
        this.throwable = throwable
    }
    override fun showError(error: Int) {}
    override fun showError(error: String) {}
    override fun showLoading() {}
}