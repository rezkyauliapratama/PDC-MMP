package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.Item
import android.rezkyauliapratama.com.mmppdc.databinding.ListItemBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemsRvAdapter(private val listItems: List<Item>) : RecyclerView.Adapter<ItemsRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listItems.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listItems[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ListItemBinding = ListItemBinding.bind(itemView)

        fun bindItem(item: Item){

            //Item information
            binding.content?.tvItemName?.text = item.item_name
            binding.content?.tvDiscountOn?.text = item.discoount_on
            binding.content?.tvDiscountBp1?.text = item.discount_bp1
            binding.content?.tvDiscountBp2?.text = item.discount_bp2
            binding.content?.tvTotalDiscount?.text = item.discount_total
            binding.content?.tvDiscountMax?.text = item.discount_max
            binding.content?.tvHppCab?.text = item.hpp_cab
            binding.content?.tvUnit?.text = item.unit
            binding.content?.tvHnaPerPcs?.text = item.hna_per_pcs
            binding.content?.tvTotalHna?.text = item.total_hna
            binding.content?.tvDesc?.text = item.description


        }
    }


}