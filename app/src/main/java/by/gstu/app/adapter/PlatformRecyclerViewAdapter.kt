package by.gstu.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.gstu.app.R
import by.gstu.app.bean.Abonent
import by.gstu.app.bean.Platform
import by.gstu.app.bean.PlatformStatus
import by.gstu.app.databinding.ItemRowBinding
import by.gstu.app.databinding.PlatformRowBinding
import by.gstu.app.listener.CardClickListener

class PlatformRecyclerViewAdapter(val onCardClick: CardClickListener<Platform>)
    : RecyclerView.Adapter<PlatformRecyclerViewAdapter.PlatformViewHolder>(), Filterable {

    val data: MutableList<Platform> = arrayListOf()

    inner class PlatformViewHolder(private val binding: PlatformRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(platform: Platform) {
                binding.model = platform
                binding.status = when(platform.isActive) {
                    true -> PlatformStatus.ACTIVE
                    else -> PlatformStatus.DISABLED
                }
                binding.itemClickListener = onCardClick
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        val binding: PlatformRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.platform_row, parent,
            false)
        return PlatformViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        val platform: Platform = data[position]
        holder.bind(platform)
    }

    fun setData(platformList: List<Platform>) {
        data.clear()
        data.addAll(platformList)
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return data.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}