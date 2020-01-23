package currencyconverter.common.presentation.navigation

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.util.*

object FlagHelper {
    private const val PATH = "flags/"
    private const val EXTENSION = ".png"

    fun getByName(name: String, context: Context): Drawable? {
        val stream = try {
            context.assets.open("$PATH${name.toLowerCase(Locale.ROOT)}$EXTENSION")
        } catch (e: Exception) { null }

        return Drawable.createFromStream(stream, null)
    }

    fun load(name: String, iv: ImageView) {
        iv.setImageDrawable(getByName(name, iv.context))
    }

}