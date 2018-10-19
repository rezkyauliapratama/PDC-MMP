package android.rezkyauliapratama.com.mmppdc.data.repository

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.network.ApiRepository
import android.rezkyauliapratama.com.mmppdc.data.network.api.LoginApi
import android.rezkyauliapratama.com.mmppdc.data.schema.UserSchema
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(val dataManager: DataManager) : ApiObservable<LoginUseCase.Listener>() {

    interface Listener {
        fun onLoginSuccess(response: LoginApi.Response)
        fun onLoginFailure(message: String)
    }

    fun loginAndNotify(userSchema: UserSchema){
        dataManager
                .api
                .login
                .doLogin(userSchema)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    dataManager.preference.setToken(response.token)
                    notifySuccess(response)

                }) { throwable ->
                    notifyFailure(throwable.localizedMessage)
                }

    }

    private fun notifyFailure(message: String) {
        for (listener in listeners) {
            listener.onLoginFailure(message)
        }
    }

    private fun notifySuccess(response: LoginApi.Response) {

        for (listener in listeners) {
            listener.onLoginSuccess(response)
        }
    }



}