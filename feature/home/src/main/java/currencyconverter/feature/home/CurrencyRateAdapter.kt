package currencyconverter.feature.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sha.kamel.navigator.FragmentNavigator
import kotlinx.android.synthetic.main.item_currency_rate.view.*
import currencyconverter.common.presentation.ui.adapter.BaseRecyclerAdapter
import currencyconverter.common.presentation.ui.adapter.BaseViewHolder
import currencyconverter.common.presentation.ui.view.ViewInterface
import currencyconverter.common.core.picasso.PicassoUtil
import currencyconverter.common.data.model.CurrencyRate
import currencyconverter.common.presentation.navigation.FlagHelper
import currencyconverter.common.presentation.navigation.Fragments
import currencyconverter.common.presentation.navigation.fragmentFrom
import kotlinx.android.synthetic.main.frag_home.*


/**
 * Created by Sha on 4/20/17.
 */

class CurrencyRateAdapter(
        list: List<CurrencyRate>,
        viewInterface: ViewInterface
) : BaseRecyclerAdapter<CurrencyRate, CurrencyRateAdapter.Vh>(list, viewInterface) {

    override fun getViewHolder(viewGroup: ViewGroup, viewType: Int) = Vh(viewGroup)

    inner class Vh internal constructor(viewGroup: ViewGroup)
        : BaseViewHolder<CurrencyRate>(viewGroup, R.layout.item_currency_rate) {

        init {
            itemView.setOnClickListener {
                val frag: Fragment = fragmentFrom(Fragments.RateCalculator)
                val bundle = Bundle().apply {
                    item?.let {
                        putString(Fragments.RateCalculator.CURRENCY_NAME, it.currency)
                        putString(Fragments.RateCalculator.CURRENCY_RATE, it.rate.toString())
                    }
                }
                frag.arguments = bundle
                FragmentNavigator(viewInterface.activity() as FragmentActivity)
                        .add(frag, true)
            }
        }

        override fun bindView(item: CurrencyRate) {
            itemView.tvCurrency.text = item.currency
            itemView.tvRate.text = item.rate.toString()
            FlagHelper.load(item.currency, itemView.ivFlag)
        }

    }
}
