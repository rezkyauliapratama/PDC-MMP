package android.rezkyauliapratama.com.mmppdc.screens.login

import android.rezkyauliapratama.com.mmppdc.screens.common.views.ObservableViewMvc

interface  LoginViewMvc : ObservableViewMvc<LoginViewMvc.Listener> {

    interface Listener {
        fun onLogin(email: String, password: String)
    }


    fun showProgressIndication()

    fun hideProgressIndication()


}