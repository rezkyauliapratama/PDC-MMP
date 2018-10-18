package android.rezkyauliapratama.com.mmppdc.screens.login

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.schema.UserSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error

class LoginController (val screensNavigator: ScreensNavigator, val dataManager: DataManager) : BaseController(), LoginViewMvc.Listener{


    override fun onLogin(email: String, password: String) {
        error { "onLogin" }
        mViewMvc.showProgressIndication()
        val userSchema = UserSchema(email = email, password = password)
        compositeDisposable.add(
                dataManager.api.
                        login.doLogin(userSchema)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({it ->
                            error { "it : ${Gson().toJson(it)}" }
                            mViewMvc.hideProgressIndication()

                            screensNavigator.toMainActivity()

                        },{t: Throwable? ->
                            error { "onerror login: ${Gson().toJson(t)}" }
                            mViewMvc.hideProgressIndication()

                        }
                        ))


    }


    private lateinit var mViewMvc: LoginViewMvc

    fun bindView(viewMvc: LoginViewMvc) {
        mViewMvc = viewMvc
    }

    fun onStart(){
        mViewMvc.registerListener(this)

        mViewMvc.hideProgressIndication()
    }

    override fun onStop(){
        mViewMvc.unregisterListener(this)
    }

}