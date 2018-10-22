package android.rezkyauliapratama.com.mmppdc

import android.app.Application
import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationComponent
import android.rezkyauliapratama.com.mmppdc.di.application.ApplicationModule
import android.rezkyauliapratama.com.mmppdc.di.application.DaggerApplicationComponent
import android.rezkyauliapratama.com.mmppdc.di.application.NetworkModule
import com.app.infideap.stylishwidget.view.Stylish
import com.squareup.leakcanary.LeakCanary


class BaseApplication : Application(){

    companion object {
        lateinit var component : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = initDagger(this)
        component.inject(this)

        LeakCanary.install(this)
        val fontFolder = "fonts/Nunito/Nunito-"

        Stylish.getInstance().set(
                fontFolder + "Regular.ttf",
                fontFolder + "Bold.ttf",
                fontFolder + "Italic.ttf",
                fontFolder + "BoldItalic.ttf"
        )

        Stylish.getInstance().fontScale = 1f
    }

    private fun initDagger(app: BaseApplication): ApplicationComponent =
            DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(app))
                    .networkModule(NetworkModule())
                    .build()



}