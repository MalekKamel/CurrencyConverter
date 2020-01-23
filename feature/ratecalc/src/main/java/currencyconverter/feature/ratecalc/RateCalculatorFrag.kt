package currencyconverter.feature.ratecalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import currencyconverter.common.data.model.CurrencyRate
import currencyconverter.common.presentation.navigation.Fragments
import currencyconverter.common.presentation.ui.frag.BaseFrag
import currencyconverter.feature.ratecalc.di.injectFeature
import kotlinx.android.synthetic.main.frag_rate_calc.*
import org.koin.android.viewmodel.ext.android.viewModel

class RateCalculatorFrag : BaseFrag<HomeViewModel>() {
    override val vm: HomeViewModel by viewModel()
    override var layoutId: Int = R.layout.frag_rate_calc
    private lateinit var currencyRate: CurrencyRate

    override fun onCreate(savedInstanceState: Bundle?) {
        injectFeature()
        super.onCreate(savedInstanceState)
    }

    override fun doOnViewCreated() {
        super.doOnViewCreated()


        showCurrencyData()
    }

    private fun showCurrencyData() {
        arguments?.run {
            currencyRate = CurrencyRate(
                    getString(Fragments.RateCalculator.CURRENCY_NAME) ?: "",
                    getString(Fragments.RateCalculator.CURRENCY_RATE)?.toDouble() ?: 0.0
            )
        }

        val base = "EUR"
        tvBaseCurrency.text = base

        tvToCurrencyName.text = currencyRate.currency
        tvToCurrencyValue.text = currencyRate.rate.toString()
        etBaseCurrency.setText("1")

        etBaseCurrency.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                val amount = text?.toString() ?: ""
                if(amount.isEmpty()){
                    tvToCurrencyValue.text = currencyRate.rate.toString()
                    return
                }
                tvToCurrencyValue.text = (amount.toDouble() * currencyRate.rate).toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}
