package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainController
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginController
import dagger.Module
import dagger.Provides

@Module
class ControllerModule {
    @Provides
    fun getLoginController(screensNavigator: ScreensNavigator, dataManager: DataManager) : LoginController{
        return LoginController(screensNavigator, dataManager)
    }

    @Provides
    fun getMainController(screensNavigator: ScreensNavigator, dataManager: DataManager) : MainController{
        return MainController(screensNavigator, dataManager)
    }

}