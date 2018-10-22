package android.rezkyauliapratama.com.mmppdc.screens.pdc.adapter

import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.ListSoBinding
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.rezkyauliapratama.com.mmppdc.utils.FormatNumber
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_so.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PdcRvAdapter(private val constant: Constant, private val clickListener : (PdcSchema) -> Unit) : RecyclerView.Adapter<PdcRvAdapter.ViewHolder>() {

    private val mItems: MutableList<PdcSchema> = mutableListOf()

    fun bindItems(listItem: List<PdcSchema>) {
        mItems.clear()
        if (!listItem.isEmpty()){
            mItems.addAll(listItem)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_so, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(mItems[position], constant, clickListener)

        holder.binding.buttonDetailInformation.onClick {
            expand(position)
        }

        holder.binding.buttonSelect.onClick {
            select(position)
        }

    }

    private fun select(position: Int) {
        val selected = mItems[position].isSelected
        mItems[position].isSelected = !selected
        // Notify the adapter that item has changed
        notifyItemChanged(position)
    }

    private fun expand(position: Int) {
        val expanded = mItems[position].isExpanded
        mItems[position].isExpanded = !expanded
        // Notify the adapter that item has changed
        notifyItemChanged(position)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var binding: ListSoBinding = ListSoBinding.bind(itemView)

        fun bindItem(pdcSchema: PdcSchema, constant: Constant, clickListener: (PdcSchema) -> Unit) {

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
                binding.containerDetailInformation.container_rvListItem.rv_listItem.layoutManager = LinearLayoutManager(itemView.context)
                binding.containerDetailInformation.container_rvListItem.rv_listItem.adapter = adapter
                binding.containerDetailInformation.tv_noItems.visibility = View.GONE
            } else {
                binding.containerDetailInformation.tv_noItems.visibility = View.VISIBLE
                binding.containerDetailInformation.container_rvListItem.visibility = View.GONE
            }

            binding.containerDetailInformation.visibility = if (pdcSchema.isExpanded) View.VISIBLE else View.GONE

            binding.buttonDetailInformation.tv_showMore.text = if (pdcSchema.isExpanded) itemView.resources.getString(R.string.hide_detail)
            else itemView.resources.getString(R.string.show_detail)

            binding.buttonSelect.tv_select.text = if (pdcSchema.isSelected) itemView.resources.getString(R.string.unselect)
            else itemView.resources.getString(R.string.select)

            binding.contentBody.background = if (pdcSchema.isSelected) itemView.resources.getDrawable(R.drawable.layerlist_round_dash_select)
            else itemView.resources.getDrawable(R.drawable.layerlist_round_dash)

            binding.buttonSelect.visibility = if (pdcSchema.approve_status.equals(constant.PDC_WAITING)) View.VISIBLE else View.GONE
        }
    }


}