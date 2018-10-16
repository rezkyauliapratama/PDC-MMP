package android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator

import android.app.Activity
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainActivity
import android.rezkyauliapratama.com.mmppdc.screens.common.FragmentFrameHelper.FragmentFrameHelper
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity


class ScreensNavigator(private val fragmentFrameHelper: FragmentFrameHelper?, private val activity: Activity) {

    fun toMainActivity(){
        activity.ctx.startActivity<MainActivity>()
    }

  /*  fun toLastEvent() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LastEventFragment.newInstance())
    }

    fun fetchDataIntoLastEvent(s : String){
        (mFragmentFrameHelper.getFragment() as LastEventFragment).setData(s)
    }*/
}
