package android.rezkyauliapratama.com.mmppdc.screens.pdc.history

import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator


class HistoryController(private val screensNavigator: ScreensNavigator, private val pdcUseCase: PdcUseCase): BaseController(),
        HistoryViewMvc.Listener, PdcUseCase.Listener{


    private lateinit var mViewMvc: HistoryViewMvc

    fun bindView(viewMvc: HistoryViewMvc) {
        mViewMvc = viewMvc
    }

    fun fetchData(){
        mViewMvc.showProgressIndication()
        pdcUseCase.PdcHistoryAndNotify()
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