package android.rezkyauliapratama.com.mmppdc.screens.pdc

import android.os.Bundle
import android.rezkyauliapratama.com.mmppdc.databinding.FragmentListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseFragment
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup

class PdcFragment :  BaseFragment<PdcController, PdcViewMvc, FragmentListSoBinding>(){

    companion object {
        fun newInstance (): PdcFragment {
            val fragment = PdcFragment()
            return fragment
        }
    }

    override fun inject() {
        controllerComponent.inject(this)
    }

    override fun initView(container: ViewGroup?) {
        mViewMvc = viewMvcFactory.getPdvMvcView(container)
        mController.bindView(mViewMvc)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as FragmentListSoBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mController.fetchData()
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