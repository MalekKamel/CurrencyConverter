package currencyconverter.common.presentation.ui.frag

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import com.sha.kamel.navigator.FragmentNavigator
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import currencyconverter.common.presentation.ui.activity.BaseActivity
import currencyconverter.common.presentation.ui.view.ViewInterface
import currencyconverter.common.core.util.reportAndPrint

/**
 * Created by Sha on 9/24/17.
 */

abstract class BaseDialogFrag : RxDialogFragment(), ViewInterface {
    protected open var transparentWindow: Boolean  = true
    protected open var isCanceledOnTouchOutside: Boolean = false
    private var onDismissListener: (() -> Unit)? = null
    abstract var layoutId: Int
    protected open fun doOnViewCreated() {}
    var isDisplayed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
        } catch (e: Exception) {
            e.reportAndPrint()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(isCanceledOnTouchOutside)

        try {
            doOnViewCreated()
        } catch (e: Exception) {
            e.reportAndPrint()
        }
    }

    @Nullable
    override fun onCreateView(
            inflater: LayoutInflater,
            @Nullable container: ViewGroup?,
            @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val w = dialog.window
        // Hide title
        w?.requestFeature(Window.FEATURE_NO_TITLE)
        if (transparentWindow) w?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun activity(): BaseActivity = activity as BaseActivity

    open fun show(activity: FragmentActivity, tag: String = javaClass.name): BaseDialogFrag {
        if (isDisplayed) return this
        FragmentNavigator(activity).showDialogFragment(this)
        isDisplayed = true
        return this
    }

    fun onDismissListener(callback: () -> Unit): BaseDialogFrag {
        this.onDismissListener = callback
        return this
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
        isDisplayed = false
    }

    override fun onDestroy() {
        super.onDestroy()
        isDisplayed = false
    }
}
