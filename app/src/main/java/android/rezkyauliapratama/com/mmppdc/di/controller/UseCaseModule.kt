package android.rezkyauliapratama.com.mmppdc.di.controller

import android.rezkyauliapratama.com.mmppdc.data.DataManager
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import android.rezkyauliapratama.com.mmppdc.data.repository.PdcUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{
    @Provides
    fun getLoginUseCase(dataManager: DataManager) : LoginUseCase{
        return LoginUseCase(dataManager)
    }

    @Provides
    fun getPdcUseCase(dataManager: DataManager) : PdcUseCase{
        return PdcUseCase(dataManager)
    }
}