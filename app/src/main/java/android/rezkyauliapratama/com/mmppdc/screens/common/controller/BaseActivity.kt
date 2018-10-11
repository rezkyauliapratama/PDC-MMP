package android.rezkyauliapratama.com.mmppdc.screens.common.controller

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.rezkyauliapratama.com.mmppdc.BaseApplication
import android.rezkyauliapratama.com.mmppdc.di.controller.ActivityModule
import android.rezkyauliapratama.com.mmppdc.di.controller.ControllerComponent
import android.rezkyauliapratama.com.mmppdc.di.controller.DaggerControllerComponent
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ViewMvc
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject


abstract class BaseActivity<T : BaseController, U : ViewMvc, V : ViewDataBinding>  : AppCompatActivity(), AnkoLogger{

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mController: T

    lateinit var mViewMvc: U

    lateinit var mDataBinding: V

    abstract fun inject()
    abstract fun initView()
    abstract fun initDataBinding()

    val controllerComponent: ControllerComponent by lazy {
        DaggerControllerComponent.builder()
                .applicationComponent(BaseApplication.component)
                .activityModule(ActivityModule(this))
                .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        initView()
        initDataBinding()

        super.onCreate(savedInstanceState)

        setContentView(mViewMvc.dataBinding?.root)
    }



}