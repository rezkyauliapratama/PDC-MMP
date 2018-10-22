package android.rezkyauliapratama.com.mmppdc.screens.pdc.adapter

import android.rezkyauliapratama.com.mmppdc.R
import android.rezkyauliapratama.com.mmppdc.data.schema.PdcSchema
import android.rezkyauliapratama.com.mmppdc.databinding.ListSoBinding
import android.rezkyauliapratama.com.mmppdc.screens.common.ViewMvcFactory
import android.rezkyauliapratama.com.mmppdc.screens.pdc.pdclistitem.PdcListItemViewMvc
import android.rezkyauliapratama.com.mmppdc.screens.pdc.waiting.PdcViewMvc
import android.rezkyauliapratama.com.mmppdc.utils.Constant
import android.rezkyauliapratama.com.mmppdc.utils.FormatNumber
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_so.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PdcRvAdapter(private val viewMvcFactory: ViewMvcFactory,
                   listener: Listener,
                   private val constant: Constant):
        RecyclerView.Adapter<PdcRvAdapter.ViewHolder>() , PdcListItemViewMvc.Listener{

    interface Listener {
        fun onPdcSelected(listPdc: List<PdcSchema>)
    }

    private val mListener: Listener = listener


    private val mItems: MutableList<PdcSchema> = mutableListOf()

    fun bindItems(listItem: List<PdcSchema>) {
        mItems.clear()
        if (!listItem.isEmpty()){
            mItems.addAll(listItem)
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvcFactory.getPdcListItemViewMvc(parent)
        viewMvc.registerListener(this)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindQuestion(mItems[position], position, constant)

    }


    override fun onPdcSelected(position: Int) {
        val selected = mItems[position].isSelected
        mItems[position].isSelected = !selected
        // Notify the adapter that item has changed
        notifyItemChanged(position)
        mListener.onPdcSelected(mItems)
    }

    override fun onPdcDetailClicked(position: Int) {
        val expanded = mItems[position].isExpanded
        mItems[position].isExpanded = !expanded
        // Notify the adapter that item has changed
        notifyItemChanged(position)

    }

    class ViewHolder(val mViewMvc: PdcListItemViewMvc) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}