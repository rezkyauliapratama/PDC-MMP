package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.rezkyauliapratama.com.mmppdc.data.network.api.SoApi
import android.rezkyauliapratama.com.mmppdc.data.repository.ApprovalUseCase
import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import android.rezkyauliapratama.com.mmppdc.data.schema.ApprovalSchema
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import com.google.gson.Gson
import com.rezkyaulia.android.light_optimization_data.NetworkClient
import org.jetbrains.anko.error

class PdcController(private val screensNavigator: ScreensNavigator, private val pdcUseCase: PdcUseCase, private val approvalUseCase: ApprovalUseCase): BaseController(),
        PdcViewMvc.Listener, PdcUseCase.Listener, ApprovalUseCase.Listener{
    override fun onApprovePDC(listPdc: MutableList<PdcSchema>) {
        error { "select : ${Gson().toJson(listPdc)}" }
        if (listPdc.isNotEmpty()){

            val arrString: MutableList<String> = mutableListOf()
            for ((index, value) in listPdc.withIndex()){
                arrString.add(value.id)
            }
            val approvalSchema = ApprovalSchema("approve",arrString)
            mViewMvc.showProgressIndication()
            approvalUseCase.approvalAndNotify(approvalSchema)

        }
    }

    override fun onRejectPDC(listPdc: MutableList<PdcSchema>) {
        error { "select : ${Gson().toJson(listPdc)}" }
        if (listPdc.isNotEmpty()){

            val arrString: MutableList<String> = mutableListOf()
            for ((index, value) in listPdc.withIndex()){
                arrString.add(value.id)
            }
            val approvalSchema = ApprovalSchema("reject",arrString)
            mViewMvc.showProgressIndication()
            approvalUseCase.approvalAndNotify(approvalSchema)

        }
    }


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
    }

    override fun onFetchPdcSuccess(response:  List<PdcSchema>) {
        mViewMvc.bindListPdc(response)
        mViewMvc.hideProgressIndication()

    }

    override fun onFetchPdcFailure(message: String) {
        error { "onFetchPdcFailure : $message" }
        mViewMvc.hideProgressIndication()
    }

    override fun onFetchApprovalSuccess(response: SoApi.ApprovalResponse) {
        mViewMvc.hideFabIndication()
        error { "approval success : ${Gson().toJson(response)}" }
        pdcUseCase.PdcWaitingStatusAndNotify()
        pdcUseCase.sendStatusToRefreshHistory(response)

    }

    override fun onFetchApprovalFailure(message: String) {
        mViewMvc.hideProgressIndication()
        error { "onFetchApprovalFailure : $message" }
    }

    fun onStart(){
        error { "onstart" }
        mViewMvc.registerListener(this)
        pdcUseCase.registerListener(this)
        approvalUseCase.registerListener(this)
    }

    fun onStop(){
        error { "onstop" }

        mViewMvc.unregisterListener(this)
        pdcUseCase.unregisterListener(this)
        approvalUseCase.unregisterListener(this)

    }




}