package by.gstu.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.R
import by.gstu.app.bean.Abonent
import by.gstu.app.databinding.ItemRowBinding
import by.gstu.app.listener.CardClickListener

class AbonentRecyclerViewAdapter
    : RecyclerView.Adapter<AbonentRecyclerViewAdapter.AbonentViewHolder>(),
        CardClickListener, Filterable {

    private val data: MutableList<Abonent> = arrayListOf()

    inner class AbonentViewHolder(private val binding: ItemRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(abonent: Abonent) {
                binding.model = abonent
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbonentViewHolder {
        val binding: ItemRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_row, parent, false)
        return AbonentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AbonentViewHolder, position: Int) {
        val abonent: Abonent = data[position]
        holder.bind(abonent)
    }

    fun setData(abonentList: List<Abonent>) {
        data.clear()
        data.addAll(abonentList)
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return data.size
    }

    override fun onCardClick(abonent: Abonent) {
        TODO("Not yet implemented")
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}