package reprator.axxess.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import reprator.axxess.base_android.SearchModal
import reprator.axxess.base_android.util.GeneralDiffUtil
import reprator.axxess.search.databinding.RowSearchItemBinding
import javax.inject.Inject

class SearchAdapter @Inject constructor(private val itemClickListener: (Int) -> Unit) :
    ListAdapter<SearchModal, VHSearch>(GeneralDiffUtil<SearchModal>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHSearch {
        val binding =
            RowSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VHSearch(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: VHSearch, position: Int) {
        holder.bind(getItem(position))
    }
}

class VHSearch(private val binding: RowSearchItemBinding, itemClickListener: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            if (bindingAdapterPosition > -1)
                itemClickListener(bindingAdapterPosition)
        }
    }

    fun bind(item: SearchModal) {
        binding.imageUrl = item.imageUrl
    }
}