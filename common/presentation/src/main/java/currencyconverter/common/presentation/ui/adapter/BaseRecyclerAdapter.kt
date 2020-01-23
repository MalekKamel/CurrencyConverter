package currencyconverter.common.presentation.ui.adapter

import android.view.ViewGroup
import currencyconverter.common.presentation.ui.view.ViewInterface
import currencyconverter.common.core.util.reportAndPrint

/**
 * Created by Sha on 4/20/17.
 */

abstract class BaseRecyclerAdapter<M, VH
: BaseViewHolder<M>>(
        var list: List<M>,
        var viewInterface: ViewInterface
) : androidx.recyclerview.widget.RecyclerView.Adapter<VH>(){

    protected var isLoadingAdded: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(viewGroup: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        try {
            val item = list[position]
            holder.item = item
            holder.bindView(item)
        } catch (e: Exception) {
            e.reportAndPrint()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun item(position: Int): M {
        return list[position]
    }

    fun list(): List<M> {
        return list
    }

}
