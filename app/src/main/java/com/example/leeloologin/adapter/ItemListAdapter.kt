package com.example.leeloologin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.leeloologin.databinding.RvCustomUserBinding
import com.example.leeloologin.model.LoginResponse

/**
 * Created by Satish V on 08-August-2022.
 * Company : HighOnSoft Innovations Pvt ltd
 * Email   : iamsatishema@gmail.com
 */

class ItemListAdapter(private val onItemClicked: (LoginResponse) -> Unit) :
    ListAdapter<LoginResponse, ItemListAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            RvCustomUserBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ItemViewHolder(private var binding: RvCustomUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LoginResponse) {
            binding.tvName.text = HtmlCompat.fromHtml(
                "Full Name : <b>${item.name}</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.name.text = HtmlCompat.fromHtml(
                "Username Name : <b>${item.login}</b>",
                HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<LoginResponse>() {
            override fun areItemsTheSame(oldItem: LoginResponse, newItem: LoginResponse): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: LoginResponse, newItem: LoginResponse): Boolean {
                return oldItem.user_id == newItem.user_id
            }
        }
    }
}