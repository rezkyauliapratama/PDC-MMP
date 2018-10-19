package android.rezkyauliapratama.com.mmppdc.data.network.api

import android.rezkyauliapratama.com.mmppdc.data.preference.PrefManager
import okhttp3.Headers
import javax.inject.Inject

abstract class BaseApi{

    @Inject
    lateinit var prefManager: PrefManager

    internal fun getUserHeaderWithToken(): Headers.Builder {
        return Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Authorization", "Bearer " + prefManager.getToken())
    }

    internal fun getUserHeader(): Headers.Builder {
        return Headers.Builder()
                .add("Content-Type", "application/json")
    }
}