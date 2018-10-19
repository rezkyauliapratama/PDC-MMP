package android.rezkyauliapratama.com.mmppdc.data

import android.rezkyauliapratama.com.mmppdc.data.network.ApiRepository
import android.rezkyauliapratama.com.mmppdc.data.preference.PrefManager
import android.rezkyauliapratama.com.mmppdc.data.repository.LoginUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var api: ApiRepository

    @Inject
    lateinit var preference: PrefManager

}
