package currencyconverter.common.presentation.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sha.rxrequester.Presentable
import com.sha.rxrequester.RxRequester
import io.reactivex.disposables.CompositeDisposable
import currencyconverter.common.presentation.R
import currencyconverter.common.presentation.rx.*
import currencyconverter.common.data.DataManager

open class BaseViewModel(val dm: DataManager) : ViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()
    var requester: RxRequester

    val toggleLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val showErrorRes = MutableLiveData<Int>()

    init { requester = setupRequester() }

    private fun setupRequester(): RxRequester {
        val presentable = object: Presentable {
            override fun showError(error: String) { showError.value = error }
            override fun showError(error: Int) { showErrorRes.value = error }
            override fun showLoading() { toggleLoading.value = true }
            override fun hideLoading() { toggleLoading.value = false }
            override fun onHandleErrorFailed(throwable: Throwable) {
                showErrorRes.value = R.string.oops_something_went_wrong
            }
        }

        return RxRequester.create(presentable) {
            httpHandlers = listOf(ServerErrorHandler())
            throwableHandlers = listOf(IoExceptionHandler(), NoSuchElementHandler(), OutOfMemoryErrorHandler())
            serverErrorContract = ErrorContract::class.java
        }
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}

