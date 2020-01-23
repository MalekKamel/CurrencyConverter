package currencyconverter.common.presentation.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sha.rxrequester.Presentable
import currencyconverter.common.data.DataManager
import currencyconverter.common.presentation.requester.AppRequester
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel(val dm: DataManager) : ViewModel() {

    val disposables: CompositeDisposable = CompositeDisposable()
    var requester: AppRequester

    val toggleLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val showErrorRes = MutableLiveData<Int>()

    init { requester = setupRequester() }

    private fun setupRequester(): AppRequester {
        val presentable = object: Presentable {
            override fun showError(error: String) { showError.value = error }
            override fun showError(error: Int) { showErrorRes.value = error }
            override fun showLoading() { toggleLoading.value = true }
            override fun hideLoading() { toggleLoading.value = false }
            override fun onHandleErrorFailed(throwable: Throwable) {
                showError.value = throwable.message
            }
        }
        return AppRequester(presentable)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}

