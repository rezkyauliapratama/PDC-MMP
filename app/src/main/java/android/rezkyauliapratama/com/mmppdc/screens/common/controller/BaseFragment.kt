package android.rezkyauliapratama.com.mmppdc.screens.common.controller

import android.app.Activity
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.rezkyauliapratama.com.mmppdc.BaseApplication
import android.rezkyauliapratama.com.mmppdc.di.controller.ActivityModule
import android.rezkyauliapratama.com.mmppdc.di.controller.ControllerComponent
import android.rezkyauliapratama.com.mmppdc.di.controller.DaggerControllerComponent
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.ViewMvc
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

abstract class BaseFragment<CONTROLLER : BaseController, VIEW_MVC : ViewMvc, DATA_BINDING : ViewDataBinding>  : Fragment(), AnkoLogger{


    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mController: CONTROLLER

    lateinit var mViewMvc: VIEW_MVC

    lateinit var mDataBinding: DATA_BINDING

    abstract fun inject()
    abstract fun initView(container: ViewGroup?)
    abstract fun initDataBinding()

    val controllerComponent: ControllerComponent by lazy {
        DaggerControllerComponent.builder()
                .applicationComponent(BaseApplication.component)
                .activityModule(ActivityModule(requireActivity()))
                .build()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Activity){
            inject()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        initView(container)
        initDataBinding()
        return mViewMvc.dataBinding?.root
    }
}