package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.screens.common.views.ObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ViewMvc

interface MainViewMvc : ObservableViewMvc<MainViewMvc.Listener> {

    interface Listener {
        fun onDrawerMenuInteraction(id: Int)
        fun onLogout()
    }

}