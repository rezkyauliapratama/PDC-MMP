package android.rezkyauliapratama.com.mmppdc.di.controller

import android.content.Context
import android.rezkyauliapratama.com.mmppdc.di.controller.ActivityContext
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides

@Module
class MvcWrapperModule{

   /* @Provides
    fun getViewMvcFactory(@ActivityContext context: Context): ViewMvcFactory {
        return ViewMvcFactory(LayoutInflater.from(context))
    }

    @Provides
    fun getScreensNavigator(activity: FragmentActivity): ScreensNavigator {
        return ScreensNavigator(getFragmentFrameHelper(activity))
    }

    @Provides
    fun getFragmentManager(activity: FragmentActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun getFragmentFrameHelper(activity: FragmentActivity): FragmentFrameHelper {
        return FragmentFrameHelper(activity, getFragmentFrameWrapper(activity), getFragmentManager(activity))
    }

    @Provides
    fun getFragmentFrameWrapper(activity: FragmentActivity): FragmentFrameWrapper {
        return activity as FragmentFrameWrapper
    }*/
}