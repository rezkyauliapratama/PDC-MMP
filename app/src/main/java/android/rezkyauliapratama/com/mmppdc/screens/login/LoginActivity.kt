package android.rezkyauliapratama.com.mmppdc.screens.login

import android.os.Bundle
import android.rezkyauliapratama.com.mmppdc.databinding.ActivityLoginBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseActivity

class LoginActivity : BaseActivity<LoginController, LoginViewMvc, ActivityLoginBinding>() {

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityLoginBinding
    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getLoginViewMvc(null)
        mController.bindView(mViewMvc)
    }

    override fun inject() {
        controllerComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        mController.onStart()
    }

    override fun onStop() {
        super.onStop()
        mController.onStop()
    }

}
