package currencyconverter.common.presentation.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import currencyconverter.common.presentation.R
import java.util.*


object FontCache {

    @SuppressLint("UseSparseArrays")
    private val fontCache = HashMap<Int, Typeface>()

    interface Font {
        companion object {
            val DEFAULT = R.font.droid_sans_arabic
        }
    }

    fun defaultTypeface(context: Context): Typeface? {
        return typeface(Font.DEFAULT, context)
    }

    fun typeface(fontRes: Int, context: Context): Typeface? {
        var typeface = fontCache[fontRes]

        if (typeface == null) {
            try {
                typeface = ResourcesCompat.getFont(context, R.font.droid_sans_arabic)
            } catch (e: Exception) {
                Log.e("font", "not applied")
                return null
            }

            fontCache[fontRes] = typeface!!
        }
        return typeface
    }

}