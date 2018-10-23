package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.screens.common.controller.BaseController
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginViewMvc

class MainController(val screensNavigator: ScreensNavigator, val dataManager: DataManager) : BaseController(), MainViewMvc.Listener{

    override fun onLogout() {
        dataManager.preference.setToken("")
        screensNavigator.toLogOut()
    }

    override fun onDrawerMenuInteraction(id: Int) {

    }

    private lateinit var mViewMvc: MainViewMvc

    fun bindView(viewMvc: MainViewMvc) {
        mViewMvc = viewMvc
    }

    fun onStart(){
        mViewMvc.registerListener(this)
    }

    fun onStop(){
        mViewMvc.unregisterListener(this)
    }


}
