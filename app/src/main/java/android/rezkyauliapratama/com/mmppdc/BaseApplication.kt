package android.rezkyauliapratama.com.mmppdc

import android.app.Application
import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationComponent
import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationModule
import android.rezkyauliapratama.com.mmppdc.di.application.DaggerApplicationComponent
import android.rezkyauliapratama.com.mmppdc.di.application.NetworkModule
import com.squareup.leakcanary.LeakCanary


class BaseApplication : Application(){

    companion object {
        lateinit var component : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
        component.inject(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    private fun initDagger(app: BaseApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(app))
                    .networkModule(NetworkModule())
                    .build()



}