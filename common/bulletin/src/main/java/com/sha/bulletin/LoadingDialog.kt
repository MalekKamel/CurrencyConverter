package com.sha.bulletin

import android.content.DialogInterface
import androidx.fragment.app.FragmentActivity

object LoadingDialog : AbstractDialog() {

    override var layoutId: Int = R.layout.frag_dialog_loading
    var options: Options? = Options.defaultOptions()
        set(value) {
            if (isDisplayed) return
            field = value
        }

    override fun isCancelable(): Boolean  = options?.isCancellable ?: false

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        cleanup()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleanup()
    }

    private fun cleanup() {
        options = null
    }

    data class Options(
            var retryCallback: (() -> Unit)? = null,
            var dismissCallback: (() -> Unit)? = null,
            var isCancellable: Boolean = false
    ){

        class Builder {
            private val options = Options()

            fun isCancellable(cancellable: Boolean): Builder {
                options.isCancellable = cancellable
                return this
            }

            fun build() = options
        }

        companion object {
            fun defaultOptions(): Options = Builder().build()
            fun create(block: Options.() -> Unit) = Options().apply { block() }
        }
    }

    fun show(activity: FragmentActivity) = super.show(activity, javaClass.name)

}
