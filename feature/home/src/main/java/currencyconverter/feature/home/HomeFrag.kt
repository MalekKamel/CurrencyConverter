package currencyconverter.feature.home

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.sha.bulletin.dialog.LoadingDialog
import currencyconverter.common.core.util.disposeBy
import kotlinx.android.synthetic.main.frag_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import currencyconverter.common.presentation.ui.frag.BaseFrag
import currencyconverter.common.core.util.linearLayoutManager
import currencyconverter.common.data.model.CurrencyRate
import currencyconverter.common.presentation.navigation.FlagHelper
import currencyconverter.feature.home.di.injectFeature

class HomeFrag : BaseFrag<HomeViewModel>() {
    override val vm: HomeViewModel by viewModel()
    override var layoutId: Int = R.layout.frag_home
    override var swipeRefreshLayoutId: Int = R.id.swipeRefresh

    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
    }

    override fun doOnViewCreated() {
        super.doOnViewCreated()
        rv = findViewById(R.id.rv)
        rv.linearLayoutManager(context)
        loadRestaurants()
    }

    private fun loadRestaurants() {
        vm.latestRates().subscribe {
            showCurrencyRates(it)
        }.disposeBy(vm.disposables)
    }

    private fun showCurrencyRates(rates: List<CurrencyRate>) {
        val base = "EUR"
        tvBaseCurrency.text = base
        FlagHelper.load(base, ivFlag)

        rv.adapter = CurrencyRateAdapter(list = rates, viewInterface = this)
        rv.scheduleLayoutAnimation()
    }

    override fun onSwipeRefresh() { loadRestaurants() }

    override fun showLoadingDialog(content: String): LoadingDialog? {
        toggleRefresh(true)
        return null
    }

    override fun dismissLoadingDialogs() {
        toggleRefresh(false)
    }

    private fun toggleRefresh(show: Boolean) { swipeRefresh.isRefreshing = show }

}
