package android.rezkyauliapratama.com.mmppdc.data.preference

import com.securepreferences.SecurePreferences

class PrefManager(val preference: SecurePreferences){
    private val TOKEN = "TOKEN"

    fun setToken(token: String) {
        val editor = preference.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return preference.getString(TOKEN, "")
    }


}