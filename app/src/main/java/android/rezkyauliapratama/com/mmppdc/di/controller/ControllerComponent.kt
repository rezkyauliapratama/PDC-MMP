package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationComponent
import android.rezkyauliapratama.com.mmppdc.screens.dashboard.MainActivity
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginActivity
import android.rezkyauliapratama.com.mmppdc.screens.pdc.history.HistoryFragment
import android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting.PdcFragment
import android.rezkyauliapratama.com.mmppdc.utils.FormatNumber
import dagger.Component

/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@PerController
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, MvcWrapperModule::class, ControllerModule::class, UseCaseModule::class])
interface ControllerComponent{

    fun getFormatNumber() : FormatNumber

    fun inject(loginActivity: LoginActivity)
    fun inject(loginActivity: MainActivity)
    fun inject(pdcFragment: PdcFragment)
    fun inject(historyFragment: HistoryFragment)

}