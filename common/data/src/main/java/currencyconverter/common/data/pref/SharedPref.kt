package currencyconverter.common.data.pref

import android.content.SharedPreferences

/**
 * Created by Sha on 4/20/17.
 */

class SharedPref(pref: SharedPreferences): AbsSharedPref(pref) {
    var token: String
        get() = getString(SPKey.TOKEN, "")
        set(value) {
            putString(value, SPKey.TOKEN)
        }

    var refreshToken: String
        get() = getString(SPKey.REFRESH_TOKEN, "")
        set(value) {
            putString(value, SPKey.REFRESH_TOKEN)
        }

    fun isLogged(): Boolean = token.isNotEmpty()
}

abstract class AbsSharedPref(private val pref: SharedPreferences) {
    private var editor: SharedPreferences.Editor = pref.edit()

    fun putString(value: String?, key: SPKey){
        editor.putString(key.name, value)
        editor.apply()
    }

    fun putInt(value: Int, key: SPKey){
        editor.putInt(key.name, value)
        editor.apply()
    }

    fun putLong(value: Long, key: SPKey){
        editor.putLong(key.name, value)
        editor.apply()
    }

    fun putFloat(value: Float, key: SPKey){
        editor.putFloat(key.name, value)
        editor.apply()
    }

    fun putBoolean(value: Boolean, key: SPKey){
        editor.putBoolean(key.name, value)
        editor.apply()
    }

    fun getString(key: SPKey, def: String): String{
        return pref.getString(key.name, def)
    }

    fun getInt(key: SPKey, def: Int): Int{
        return pref.getInt(key.name, def)
    }

    fun getLong(key: SPKey, def: Long): Long{
        return pref.getLong(key.name, def)
    }

    fun getFloat(key: SPKey, def: Float): Float{
        return pref.getFloat(key.name, def)
    }

    fun getBoolean(key: SPKey, def: Boolean): Boolean{
        return pref.getBoolean(key.name, def)
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }
}

enum class SPKey {
    TOKEN,
    REFRESH_TOKEN
}

