package android.rezkyauliapratama.com.mmppdc.screens.common

import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainViewMvcImpl
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginViewMvcImpl
import android.rezkyauliapratama.com.mmppdc.screens.pdc.PdcViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.PdcViewMvcImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {

    fun getLoginViewMvc(parent: ViewGroup?): LoginViewMvc {
        return LoginViewMvcImpl(mLayoutInflater, parent, this)
    }


    fun getMainViewMvc(parent: ViewGroup?): MainViewMvc {
        return MainViewMvcImpl(mLayoutInflater, parent, this)
    }

    fun getPdvMvcView(parent: ViewGroup?): PdcViewMvc{
        return PdcViewMvcImpl(mLayoutInflater, parent, this)

    }
}
