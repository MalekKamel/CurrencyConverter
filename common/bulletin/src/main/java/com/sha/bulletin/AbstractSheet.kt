package com.sha.bulletin

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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Created by Sha on 9/24/17.
 */

abstract class AbstractSheet : BottomSheetDialogFragment() {
    open var transparentWindow: Boolean  = true
    open var isCanceledOnTouchOutside: Boolean = false
    private var onDismissListener: (() -> Unit)? = null
    abstract var layoutId: Int
    open var isDisplayed: Boolean = false
    open var hasTitle: Boolean = false
    open var transparentBackground: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       if(transparentBackground) setStyle(STYLE_NORMAL, R.style.SheetDialogTheme);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
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
        if(!hasTitle) w?.requestFeature(Window.FEATURE_NO_TITLE)
        if (transparentWindow) w?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    open fun show(activity: FragmentActivity, tag: String = javaClass.name): AbstractSheet {
        if (isDisplayed) return this
        show(activity.supportFragmentManager, tag)
        isDisplayed = true
        return this
    }

    fun onDismissListener(callback: () -> Unit): AbstractSheet {
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