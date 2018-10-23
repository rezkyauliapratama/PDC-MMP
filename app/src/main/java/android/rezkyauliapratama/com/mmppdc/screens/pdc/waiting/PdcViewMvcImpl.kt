package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.databinding.DataBindingUtil
import android.graphics.Typeface
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.FragmentListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.adapter.PdcRvAdapter
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.infideap.stylishwidget.view.Stylish
import com.google.gson.Gson
import org.jetbrains.anko.error
import org.jetbrains.anko.sdk25.coroutines.onClick

class PdcViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory,val constant: Constant) :
        BaseObservableViewMvc<PdcViewMvc.Listener>(), PdcViewMvc, PdcRvAdapter.Listener {


    var binding : FragmentListSoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_so,parent,false)
    private var adapter: PdcRvAdapter

    val listPdc : MutableList<PdcSchema> = mutableListOf()

    init{
        dataBinding = binding


        adapter = PdcRvAdapter(viewMvcFactory,this,constant)
        binding.rvListSo.layoutManager = LinearLayoutManager(getContext())
        binding.rvListSo.adapter = adapter

        binding.swipeRefreshListSo.isEnabled = false

        binding.fab.visibility = View.GONE

        binding.fab.setFabTypeface(Stylish.getInstance().getTypeface(getContext(),"fonts/Nunito/Nunito-Regular.ttf",0))

        binding.fab.onClick {
            for(listener in listeners){
                listener.onSelectPDC(listPdc)
            }
        }
    }

    override fun onPdcSelected(listPdc: List<PdcSchema>) {
        val filteredMap = listPdc.filter {it.isSelected}

        this.listPdc.addAll(filteredMap)

        if (filteredMap.size == 0){
            binding.fab.visibility = View.GONE
            binding.fab.setFabText("")
            binding.fab.fabTextVisibility = View.GONE
        }else{
            binding.fab.visibility = View.VISIBLE
            binding.fab.fabTextVisibility = View.VISIBLE
            binding.fab.setFabText("${filteredMap.size} so(s)")
        }

    }

    override fun bindListPdc(pdcs: List<PdcSchema>) {
        adapter.bindItems(pdcs)
    }

    override fun showProgressIndication() {
        binding.swipeRefreshListSo.isRefreshing = true
    }

    override fun hideProgressIndication() {
        binding.swipeRefreshListSo.isRefreshing = false
    }

}