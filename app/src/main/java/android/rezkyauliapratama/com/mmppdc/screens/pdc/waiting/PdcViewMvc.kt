package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ObservableViewMvc


interface PdcViewMvc: ObservableViewMvc<PdcViewMvc.Listener>{

    interface Listener {
        fun onClickDetailInformation()
        fun onSelectPDC(listPdc : List<PdcSchema>)
    }


    fun bindListPdc(pdcs: List<PdcSchema>)

    fun showProgressIndication()

    fun hideProgressIndication()


}