package android.rezkyauliapratama.com.mmppdc.screens.login

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.network.api.LoginApi
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import android.rezkyauliapratama.com.mmppdc.data.schema.UserSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.widget.Toast
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error

class LoginController (val screensNavigator: ScreensNavigator, val loginUseCase: LoginUseCase) : BaseController()
        , LoginViewMvc.Listener , LoginUseCase.Listener{

    override fun onLogin(userId: String, password: String) {
        mViewMvc.showProgressIndication()
        val userSchema = UserSchema(userid = userId, password = password)
        loginUseCase.loginAndNotify(userSchema)
    }


    private lateinit var mViewMvc: LoginViewMvc

    fun bindView(viewMvc: LoginViewMvc) {
        mViewMvc = viewMvc
    }

    override fun onLoginSuccess() {
        mViewMvc.hideProgressIndication()
        screensNavigator.toMainActivity()
    }

    override fun onLoginFailure(message: String) {
        mViewMvc.hideProgressIndication()
        error { "failure : $message" }
    }



    fun onStart(){
        mViewMvc.registerListener(this)
        mViewMvc.hideProgressIndication()
        loginUseCase.registerListener(this)

        loginUseCase.isDirectToMainActivity()

    }

    override fun onStop(){
        super.onStop()
        mViewMvc.unregisterListener(this)
        loginUseCase.unregisterListener(this)
    }

}