package android.rezkyauliapratama.com.mmppdc.data

import android.rezkyauliapratama.com.mmppdc.data.network.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var api: ApiRepository

}
