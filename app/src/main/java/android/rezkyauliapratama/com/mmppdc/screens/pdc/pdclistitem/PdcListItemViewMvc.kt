package android.rezkyauliapratama.com.mmppdc.screens.pdc.pdclistitem

import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.utils.Constant

/**
 * Created by Rezky Aulia Pratama on 23/10/18.
 */

interface PdcListItemViewMvc : ObservableViewMvc<PdcListItemViewMvc.Listener> {

    interface Listener {
        fun onPdcSelected(position: Int)
        fun onPdcDetailClicked(position: Int)
    }

    fun bindQuestion(pdcSchema: PdcSchema, position: Int, constant: Constant)
}
