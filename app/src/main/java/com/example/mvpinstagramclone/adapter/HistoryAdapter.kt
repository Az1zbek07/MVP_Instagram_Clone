package com.example.mvpinstagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpinstagramclone.databinding.HistoryLayoutBinding
import com.example.mvpinstagramclone.model.Photo

class HistoryAdapter: ListAdapter<Photo, HistoryAdapter.VHolder> (DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.VHolder {
        return VHolder(HistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryAdapter.VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: HistoryLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: Photo){
            binding.apply {
                Glide.with(imageView).load(photo.url).into(imageView)
            }
        }
    }
}