package android.rezkyauliapratama.com.mmppdc.screens.login

import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import org.jetbrains.anko.ctx
import org.jetbrains.anko.error
import org.jetbrains.anko.startActivity

class LoginController (val screensNavigator: ScreensNavigator) : BaseController(), LoginViewMvc.Listener{

    override fun onLogin(email: String, password: String) {
        error { "onLogin" }
        screensNavigator.toMainActivity()
    }


    private lateinit var mViewMvc: LoginViewMvc

    fun bindView(viewMvc: LoginViewMvc) {
        mViewMvc = viewMvc
    }

    fun onStart(){
        mViewMvc.registerListener(this)
    }

    fun onStop(){
        mViewMvc.unregisterListener(this)
    }

}