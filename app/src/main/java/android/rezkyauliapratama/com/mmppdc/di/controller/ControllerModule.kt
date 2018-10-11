package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginController
import dagger.Module
import dagger.Provides

@Module
class ControllerModule {
    @Provides
    fun getLastEventController(screensNavigator: ScreensNavigator) : LoginController{
        return LoginController(screensNavigator)
    }

}