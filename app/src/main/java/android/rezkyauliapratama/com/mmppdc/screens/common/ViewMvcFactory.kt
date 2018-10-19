package android.rezkyauliapratama.com.mmppdc.screens.common

import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainViewMvcImpl
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginViewMvcImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {

    fun getLoginViewMvc(parent: ViewGroup?): LoginViewMvc {
        return LoginViewMvcImpl(mLayoutInflater, parent, this)
    }


    fun getMainViewMvc(parent: ViewGroup?): MainViewMvcImpl {
        return MainViewMvcImpl(mLayoutInflater, parent, this)
    }
}
