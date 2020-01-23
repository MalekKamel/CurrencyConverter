package currencyconverter.common.data.pref

import android.content.SharedPreferences

/**
 * Created by Sha on 4/20/17.
 */

class SharedPref(private val pref: SharedPreferences) {

    private var editor: SharedPreferences.Editor = pref.edit()

    private fun putString(value: String?, key: Key){
        editor.putString(key.name, value)
        editor.apply()
    }

    private fun putInt(value: Int, key: Key){
        editor.putInt(key.name, value)
        editor.apply()
    }

    private fun putLong(value: Long, key: Key){
        editor.putLong(key.name, value)
        editor.apply()
    }

    private fun putFloat(value: Float, key: Key){
        editor.putFloat(key.name, value)
        editor.apply()
    }

    private fun putBoolean(value: Boolean, key: Key){
        editor.putBoolean(key.name, value)
        editor.apply()
    }

    private fun getString(key: Key, def: String): String{
        return pref.getString(key.name, def)
    }

    private fun getInt(key: Key, def: Int): Int{
        return pref.getInt(key.name, def)
    }

    private fun getLong(key: Key, def: Long): Long{
        return pref.getLong(key.name, def)
    }

    private fun getFloat(key: Key, def: Float): Float{
        return pref.getFloat(key.name, def)
    }

    private fun getBoolean(key: Key, def: Boolean): Boolean{
        return pref.getBoolean(key.name, def)
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

    ///// Impl

    private enum class Key{
        TOKEN,
        REFRESH_TOKEN
    }

    var token: String
        get() = getString(Key.TOKEN, "")
        set(value) {
            putString(value, Key.TOKEN)
        }

    var refreshToken: String
        get() = getString(Key.REFRESH_TOKEN, "")
        set(value) {
            putString(value, Key.REFRESH_TOKEN)
        }

    fun isLogged(): Boolean = token.isNotEmpty()




}
