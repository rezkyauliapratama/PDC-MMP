package android.rezkyauliapratama.com.mmppdc.screens.pdc

import android.databinding.DataBindingUtil
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.FragmentListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup

class PdcViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) :
        BaseObservableViewMvc<PdcViewMvc.Listener>(), PdcViewMvc{


    var binding : FragmentListSoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_so,parent,false)
    private var adapter: PdcRvAdapter

    init{
        dataBinding = binding


        adapter = PdcRvAdapter { pdc: PdcSchema -> detailInformationClicked(pdc) }
        binding.rvListSo.layoutManager = LinearLayoutManager(getContext())
        binding.rvListSo.adapter = adapter
    }



    private fun detailInformationClicked(pdc: PdcSchema) {

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