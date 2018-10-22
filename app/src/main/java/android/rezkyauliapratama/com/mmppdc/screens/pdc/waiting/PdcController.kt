package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import org.jetbrains.anko.error

class PdcController(private val screensNavigator: ScreensNavigator, private val pdcUseCase: PdcUseCase): BaseController(),
        PdcViewMvc.Listener, PdcUseCase.Listener{


    private lateinit var mViewMvc: PdcViewMvc

    fun bindView(viewMvc: PdcViewMvc) {
        mViewMvc = viewMvc
    }

    fun fetchData(){
        mViewMvc.showProgressIndication()
        pdcUseCase.PdcWaitingStatusAndNotify()
    }
    override fun onClickDetailInformation() {
        error { "onClickDetailInformation" }
        mViewMvc.hideProgressIndication()
    }

    override fun onFetchPdcSuccess(response:  List<PdcSchema>) {
        mViewMvc.bindListPdc(response)
        mViewMvc.hideProgressIndication()

    }

    override fun onFetchPdcFailure(message: String) {
        error { "onFetchPdcFailure : $message" }
    }

    fun onStart(){
        mViewMvc.registerListener(this)
        pdcUseCase.registerListener(this)
    }

    override fun onStop(){
        super.onStop()
        mViewMvc.unregisterListener(this)
        pdcUseCase.unregisterListener(this)
    }




}