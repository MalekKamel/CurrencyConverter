package currencyconverter.common.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import com.sha.formvalidator.widget.FormEditText

/**
 * Created by Sha on 9/20/17.
 */

class AppEditText : FormEditText {

    constructor(context: Context) : super(context) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        customize(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        customize(context)
    }

    private fun customize(context: Context) {
        val customFont = FontCache.defaultTypeface(context)
        typeface = customFont
    }


}
