package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.repository.ApprovalUseCase
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import android.rezkyauliapratama.com.mmppdc.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainController
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginController
import android.rezkyauliapratama.com.mmppdc.screens.pdc.history.HistoryController
import android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting.PdcController
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
    fun getPdcController(screensNavigator: ScreensNavigator, pdcUseCase: PdcUseCase, approvalUseCase: ApprovalUseCase) : PdcController {
        return PdcController(screensNavigator, pdcUseCase,approvalUseCase)
    }

    @Provides
    fun getHistoryController(screensNavigator: ScreensNavigator, pdcUseCase: PdcUseCase) : HistoryController {
        return HistoryController(screensNavigator, pdcUseCase)
    }
}