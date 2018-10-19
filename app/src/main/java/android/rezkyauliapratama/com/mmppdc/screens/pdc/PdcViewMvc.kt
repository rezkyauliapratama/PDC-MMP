package android.rezkyauliapratama.com.mmppdc.screens.pdc

import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ObservableViewMvc


interface PdcViewMvc: ObservableViewMvc<PdcViewMvc.Listener>{

    interface Listener {
        fun onClickDetailInformation()
    }


    fun bindListPdc(pdcs: List<PdcSchema>)

    fun showProgressIndication()

    fun hideProgressIndication()


}