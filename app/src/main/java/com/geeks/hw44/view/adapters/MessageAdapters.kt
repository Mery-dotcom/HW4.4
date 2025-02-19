package com.geeks.hw44.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geeks.hw44.databinding.ItemMessageBinding
import com.geeks.hw44.model.models.MessageResponse

class MessageAdapters(
    private val clickListener: (MessageResponse) -> Unit,
    private val longClickListener: (MessageResponse) -> Unit
): ListAdapter<MessageResponse, MessageAdapters.MessageViewHolder>(DuffUtilCallback()) {

    class MessageViewHolder (
        val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val messages = getItem(position)
        with(holder) {
            binding.text.text = messages.message
            itemView.setOnClickListener {
                clickListener(messages)
            }
            itemView.setOnLongClickListener {
                longClickListener(messages)
                true
            }
        }
    }
}

    class DuffUtilCallback : DiffUtil.ItemCallback<MessageResponse>() {
        override fun areItemsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem == newItem
        }
    }