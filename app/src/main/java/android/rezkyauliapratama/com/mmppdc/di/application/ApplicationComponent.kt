package android.rezkyauliapratama.com.mmppdc.di.application

import android.rezkyauliapratama.com.mmppdc.BaseApplication
import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, PreferenceModule::class])
interface ApplicationComponent{

    fun inject(baseApplication: BaseApplication)

    fun getDataManager(): DataManager
    fun getConstant(): Constant

}