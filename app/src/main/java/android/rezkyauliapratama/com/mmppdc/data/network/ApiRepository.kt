package android.rezkyauliapratama.com.mmppdc.data.network

import android.rezkyauliapratama.com.mmppdc.data.repository.LoginApi
import android.rezkyauliapratama.com.mmppdc.data.schema.LoginSchema
import android.rezkyauliapratama.com.mmppdc.data.schema.UserSchema
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Rezky Aulia Pratama on 11/9/18.
 */


@Singleton
class ApiRepository @Inject constructor(){


    @Inject
    lateinit var login: LoginApi

}