package android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting

import android.databinding.DataBindingUtil
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.FragmentListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.adapter.PdcRvAdapter
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.infideap.stylishwidget.view.Stylish
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
           dialogActionApproval()
        }
    }

    //TODO migrate this logic into controller
    override fun onPdcSelected(listPdc: List<PdcSchema>) {
        val filteredMap = listPdc.filter {it.isSelected}

        this.listPdc.clear()
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

    override fun hideFabIndication() {
        binding.fab.visibility = View.GONE
        binding.fab.setFabText("")
        binding.fab.fabTextVisibility = View.GONE
    }

    fun dialogActionApproval() {
        val builder = getContext()?.let {
            AlertDialog.Builder(it)
                    .setMessage(R.string.action_approve_confirmation)

                    .setPositiveButton(R.string.approve) { dialog, _ ->
                        for(listener in listeners){
                            listener.onApprovePDC(listPdc)
                        }
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.reject) { dialog, _ ->
                        for(listener in listeners){
                            listener.onRejectPDC(listPdc)
                        }
                        dialog.dismiss()
                    }
        }

        val dialog = builder?.create()
        dialog?.show()
    }


}