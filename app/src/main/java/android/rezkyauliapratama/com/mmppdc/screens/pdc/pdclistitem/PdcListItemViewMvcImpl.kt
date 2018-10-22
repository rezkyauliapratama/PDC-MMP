package android.rezkyauliapratama.com.mmppdc.screens.pdc.pdclistitem

import android.databinding.DataBindingUtil
import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.ListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.views.BaseObservableViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.adapter.ItemsRvAdapter
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_so.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Rezky Aulia Pratama on 23/10/18.
 */

class PdcListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseObservableViewMvc<PdcListItemViewMvc.Listener>(), PdcListItemViewMvc {


    var binding: ListSoBinding = DataBindingUtil.inflate(inflater, R.layout.list_so,parent,false)

    init {
        dataBinding = binding
    }

    override fun bindQuestion(pdcSchema: PdcSchema, position: Int, constant: Constant) {

        //so information
        binding.contentSoInformation?.tvCustomerCode?.text = pdcSchema.customer_code
        binding.contentSoInformation?.tvCustomerName?.text = pdcSchema.customer_name
        binding.contentSoInformation?.tvCustomerCategory?.text = pdcSchema.customer_category
        binding.contentSoInformation?.tvCustomerType?.text = pdcSchema.customer_type
        binding.contentSoInformation?.tvTotalCredit?.text = pdcSchema.total_credit
        binding.contentSoInformation?.tvCreditTerm?.text = pdcSchema.credit_term

        //discount item information
        binding.contentPdcInformation?.tvTotalItem?.text = pdcSchema.totalItem.toString()
        binding.contentPdcInformation?.tvApprovalStatus?.text = pdcSchema.approve_status

        if (pdcSchema.totalItem > 0) {
            val adapter = ItemsRvAdapter(pdcSchema.items)
            binding.containerDetailInformation.container_rvListItem.visibility = View.VISIBLE
            binding.containerDetailInformation.container_rvListItem.rv_listItem.layoutManager = LinearLayoutManager(getContext())
            binding.containerDetailInformation.container_rvListItem.rv_listItem.adapter = adapter
            binding.containerDetailInformation.tv_noItems.visibility = View.GONE
        } else {
            binding.containerDetailInformation.tv_noItems.visibility = View.VISIBLE
            binding.containerDetailInformation.container_rvListItem.visibility = View.GONE
        }

        binding.containerDetailInformation.visibility = if (pdcSchema.isExpanded) View.VISIBLE else View.GONE

        binding.buttonDetailInformation.tv_showMore.text = if (pdcSchema.isExpanded) getContext()?.resources?.getString(R.string.hide_detail)
        else getContext()?.resources?.getString(R.string.show_detail)

        binding.buttonSelect.tv_select.text = if (pdcSchema.isSelected) getContext()?.resources?.getString(R.string.unselect)
        else getContext()?.resources?.getString(R.string.select)

        binding.contentBody.background = if (pdcSchema.isSelected) getContext()?.resources?.getDrawable(R.drawable.layerlist_round_dash_select)
        else getContext()?.resources?.getDrawable(R.drawable.layerlist_round_dash)

        binding.buttonSelect.visibility = if (pdcSchema.approve_status.equals(constant.PDC_WAITING)) View.VISIBLE else View.GONE


        binding.buttonDetailInformation.onClick {
            for (listener in listeners){
                listener.onPdcDetailClicked(position)
            }
        }

        binding.buttonSelect.onClick {
            for (listener in listeners){
                listener.onPdcSelected(position)
            }
        }

    }
}
