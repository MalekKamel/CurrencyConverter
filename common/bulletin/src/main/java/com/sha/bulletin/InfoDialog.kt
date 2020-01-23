package com.sha.bulletin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object InfoDialog : AbstractDialog() {
    enum class MessageType { INFO, WARNING, EXCEPTION }
    var options: Options? = Options.defaultOptions()
        set(value) {
            if (isDisplayed) return
            field = value
        }

    override var layoutId: Int = R.layout.frag_dialog_info

    private val tvMessage: TextView
    get() = view!!.findViewById(R.id.tvMessage)

    private val btnDismiss: Button
        get() = view!!.findViewById(R.id.btnDismiss)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.setCanceledOnTouchOutside(options?.isCancellable ?: true)

        tvMessage.text = options?.message

        var color = -1
            when (options?.messageType) {
                MessageType.WARNING -> {
                    color = R.color.warning
                    tvMessage.setTextColor(ContextCompat.getColor(context!!, R.color.warning))
                }
                MessageType.EXCEPTION -> color = R.color.exception
                else -> {}
            }

        if (color != -1) tvMessage.setTextColor(ContextCompat.getColor(context!!, color))

        btnDismiss.setOnClickListener {
            options?.dismissCallback?.invoke()
            // Try to close all dialogs if duplicated
            dismiss()
        }
    }

    data class Options(
            var message: String? = null,
            var retryCallback: (() -> Unit)? = null,
            var dismissCallback: (() -> Unit)? = null,
            var isCancellable: Boolean = true,
            var messageType: MessageType? = null
    ){

        class Builder {
            private val options = Options()

            fun message(message: String?): Builder {
                options.message = message
                return this
            }

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

            fun messageType(type: MessageType?): Builder {
                options.messageType = type
                return this
            }

            fun build() = options
        }

        companion object {
            fun defaultOptions(): Options = Builder().build()
            fun create(type: MessageType, block: Options.() -> Unit) = Options().apply {
                messageType = type
                block() }
        }
    }

    fun show(activity: FragmentActivity) = super.show(activity, javaClass.name)
}

