package currencyconverter.common.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet

class CustomBtn : androidx.appcompat.widget.AppCompatButton {

    constructor(context: Context) : super(context) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        customize(context)
    }

    private fun customize(context: Context) {
        val customFont = FontCache.defaultTypeface(context)
        typeface = customFont
    }


}
