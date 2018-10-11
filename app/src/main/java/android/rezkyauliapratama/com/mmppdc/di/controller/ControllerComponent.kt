package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationComponent
import android.rezkyauliapratama.com.mmppdc.screens.login.LoginActivity
import dagger.Component

/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@PerController
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, MvcWrapperModule::class, ControllerModule::class])
interface ControllerComponent{
    fun inject(loginActivity: LoginActivity)

}