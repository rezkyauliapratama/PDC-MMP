package android.rezkyauliapratama.com.mmppdc.screens.common.views

import android.content.Context
import android.databinding.ViewDataBinding
import android.support.annotation.StringRes


abstract class BaseViewMvc: ViewMvc {

    override var dataBinding: ViewDataBinding? = null

    protected fun getContext(): Context? {
        return dataBinding?.root?.context
    }

    protected fun getString(@StringRes id: Int): String? {
        return getContext()?.getString(id)
    }
}
