package android.rezkyauliapratama.com.mmppdc.screens.dashboard

import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.ItemSchema
import android.rezkyauliapratama.com.mmppdc.databinding.ListItemBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemsRvAdapter(private val listItemSchemas: List<ItemSchema>) : RecyclerView.Adapter<ItemsRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listItemSchemas.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listItemSchemas[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ListItemBinding = ListItemBinding.bind(itemView)

        fun bindItem(itemSchema: ItemSchema){

            //ItemSchema information
            binding.content?.tvItemName?.text = itemSchema.item_name
            binding.content?.tvDiscountOn?.text = itemSchema.discoount_on
            binding.content?.tvDiscountBp1?.text = itemSchema.discount_bp1
            binding.content?.tvDiscountBp2?.text = itemSchema.discount_bp2
            binding.content?.tvTotalDiscount?.text = itemSchema.discount_total
            binding.content?.tvDiscountMax?.text = itemSchema.discount_max
            binding.content?.tvHppCab?.text = itemSchema.hpp_cab
            binding.content?.tvUnit?.text = itemSchema.unit
            binding.content?.tvHnaPerPcs?.text = itemSchema.hna_per_pcs
            binding.content?.tvTotalHna?.text = itemSchema.total_hna
            binding.content?.tvDesc?.text = itemSchema.description


        }
    }


}