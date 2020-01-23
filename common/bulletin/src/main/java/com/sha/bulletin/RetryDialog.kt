package com.sha.bulletin

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

object RetryDialog : AbstractDialog() {

    var options: Options? = Options.defaultOptions()
        set(value) {
            if (isDisplayed) return
            field = value
        }

    override var layoutId: Int = R.layout.frag_dialog_retry

    private val tvMessage: TextView
        get() = view!!.findViewById(R.id.tvMessage)

    private val btnRetry: Button
        get() = view!!.findViewById(R.id.btnRetry)

    private val btnDismiss: TextView
        get() = view!!.findViewById(R.id.btnDismiss)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        options?.message?.let { tvMessage.text = it }
        btnRetry.setOnClickListener {
            options?.retryCallback?.invoke()
            dismiss()
        }

        btnDismiss.setOnClickListener {
            options?.dismissCallback?.invoke()
            dismiss()
        }
    }

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
            var isCancellable: Boolean = true,
            var message: String? = null
    ){

        class Builder {
            private val options = Options()

            fun retryCallback(callback: (() -> Unit)?): Builder {
                options.retryCallback = callback
                return this
            }

            fun dismissCallback(callback: (() -> Unit)?): Builder {
                options.dismissCallback = callback
                return this
            }

            fun isCancellable(cancellable: Boolean): Builder {
                options.isCancellable = cancellable
                return this
            }

            fun message(message: String?): Builder {
                options.message = message
                return this
            }

            fun build() = options
        }

        companion object {
            fun defaultOptions(): Options = Builder().build()
            fun create(message: String?, block: Options.() -> Unit) = Options().apply {
                this.message = message
                block()
            }
        }
    }

    fun show(activity: FragmentActivity) = super.show(activity, javaClass.name)

}


