package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainController
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginController
import android.rezkyauliapratama.com.mmppdc.screens.pdc.PdcController
import dagger.Module
import dagger.Provides

@Module
class ControllerModule {
    @Provides
    fun getLoginController(screensNavigator: ScreensNavigator, loginUseCase: LoginUseCase) : LoginController{
        return LoginController(screensNavigator, loginUseCase)
    }

    @Provides
    fun getMainController(screensNavigator: ScreensNavigator, dataManager: DataManager) : MainController{
        return MainController(screensNavigator, dataManager)
    }

    @Provides
    fun getPdcController(screensNavigator: ScreensNavigator, pdcUseCase: PdcUseCase) : PdcController{
        return PdcController(screensNavigator, pdcUseCase)
    }
}