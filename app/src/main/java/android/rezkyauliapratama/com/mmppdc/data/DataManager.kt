package android.rezkyauliapratama.com.mmppdc.data

import android.rezkyauliapratama.com.mmppdc.data.EventBus.RxBus
import android.rezkyauliapratama.com.mmppdc.data.network.ApiRepository
import android.rezkyauliapratama.com.mmppdc.data.preference.PrefManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var api: ApiRepository

    @Inject
    lateinit var preference: PrefManager

    @Inject
    lateinit var rxBus: RxBus

}
