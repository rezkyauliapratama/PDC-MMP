package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseActivity

class MainActivity  : BaseActivity<MainController, MainViewMvc, ActivityMainBinding>() {
    override fun inject() {
        controllerComponent.inject(this)
    }

    override fun initView() {
        mViewMvc = viewMvcFactory.getMainViewMvc(null)
        mController.bindView(mViewMvc)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityMainBinding
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
