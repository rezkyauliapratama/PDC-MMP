package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.Pdc
import android.rezkyauliapratama.com.mmppdc.databinding.ListSoBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_so.view.*

class PdcRvAdapter(private val listPdc: List<Pdc>, private val clickListener : (String) -> Unit) : RecyclerView.Adapter<PdcRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_so, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listPdc.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listPdc[position], clickListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ListSoBinding = ListSoBinding.bind(itemView)

        fun bindItem(pdc: Pdc, clickListener: (String) -> Unit){

            //so information
            binding.contentSoInformation?.tvCustomerCode?.text = pdc.customer_code
            binding.contentSoInformation?.tvCustomerName?.text = pdc.customer_name
            binding.contentSoInformation?.tvCustomerCategory?.text = pdc.customer_category
            binding.contentSoInformation?.tvCustomerType?.text = pdc.customer_type
            binding.contentSoInformation?.tvTotalCredit?.text = pdc.total_credit
            binding.contentSoInformation?.tvCreditTerm?.text = pdc.credit_term

            //discount item information
            binding.contentPdcInformation?.tvTotalItem?.text = pdc.totalItem.toString()
            binding.contentPdcInformation?.tvApprovalStatus?.text = pdc.approve_status

            if(pdc.totalItem > 0){
                val adapter  = ItemsRvAdapter(pdc.items)
                binding.containerDetailInformation.container_rvListItem.visibility = View.VISIBLE
                binding.containerDetailInformation.container_rvListItem.rv_listItem.layoutManager = LinearLayoutManager(itemView.context)
                binding.containerDetailInformation.container_rvListItem.rv_listItem.adapter = adapter
                binding.containerDetailInformation.tv_noItems.visibility = View.GONE
            }else{
                binding.containerDetailInformation.tv_noItems.visibility = View.VISIBLE
                binding.containerDetailInformation.container_rvListItem.visibility = View.GONE
            }

        }
    }


}