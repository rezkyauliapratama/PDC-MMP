package android.rezkyauliapratama.com.mmppdc.screens.login

import android.databinding.DataBindingUtil
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.databinding.ActivityLoginBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.FragmentFrameHelper.FragmentFrameWrapper
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.sdk25.coroutines.onClick


class LoginViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
        BaseObservableViewMvc<LoginViewMvc.Listener>(), LoginViewMvc {

    var binding : ActivityLoginBinding = DataBindingUtil.inflate(inflater, R.layout.activity_login,parent,false)

    init{
        dataBinding = binding

        binding.content?.btnLogin?.onClick {
            for(listener in listeners){
                listener.onLogin("","")
            }
        }

    }

    override fun showProgressIndication() {
        binding.layoutProgress.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        binding.layoutProgress.visibility = View.GONE

    }




}