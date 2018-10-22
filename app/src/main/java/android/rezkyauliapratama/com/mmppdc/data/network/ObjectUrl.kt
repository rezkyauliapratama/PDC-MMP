package android.rezkyauliapratama.com.mmppdc.data.network

import android.net.Uri
import android.rezkyauliapratama.com.mmppdc.BuildConfig
import org.jetbrains.anko.AnkoLogger

//class yang berisi url untuk API
object ObjectUrl : AnkoLogger {


    fun login(): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("users")
                .appendPath("login.json")
                .build()
                .toString()
    }

    fun soWaiting(): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("salesorders")
                .appendPath("get_so_waiting.json")
                .build()
                .toString()
    }

    fun soHistory(): String{
            return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                    .appendPath("salesorders")
                    .appendPath("get_so_approval.json")
                    .build()
                    .toString()
        }


}