package com.example.mvpinstagramclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpinstagramclone.databinding.LittleImageLayoutBinding
import com.example.mvpinstagramclone.model.Photo

class LittleImageAdapter: ListAdapter<Photo, LittleImageAdapter.VHolder> (DiffCallback()) {
    private class DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LittleImageAdapter.VHolder {
        return VHolder(LittleImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LittleImageAdapter.VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: LittleImageLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: Photo){
            binding.apply {
                Glide.with(imageView).load(photo.url).into(imageView)
            }
        }
    }
}