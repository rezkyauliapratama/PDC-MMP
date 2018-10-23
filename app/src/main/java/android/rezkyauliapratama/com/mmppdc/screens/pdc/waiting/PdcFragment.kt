package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.os.Bundle
import android.rezkyauliapratama.com.mmppdc.databinding.FragmentListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseFragment
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class PdcFragment :  BaseFragment<PdcController, PdcViewMvc, FragmentListSoBinding>(){

    @Inject
    lateinit var constant: Constant

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
        mViewMvc = viewMvcFactory.getPdvMvcView(container, constant)
        mController.bindView(mViewMvc)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as FragmentListSoBinding
    }

    override fun onStart() {
        super.onStart()
        mController.onStart()
        mController.fetchData()

    }

    override fun onStop() {
        super.onStop()
        mController.onStop()
    }


}