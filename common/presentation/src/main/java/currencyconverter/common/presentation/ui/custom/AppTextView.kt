package currencyconverter.common.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet

class AppTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {
        customize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        customize()
    }

    private fun customize() {
        val customFont = FontCache.defaultTypeface(context)
        typeface = customFont
    }

}
