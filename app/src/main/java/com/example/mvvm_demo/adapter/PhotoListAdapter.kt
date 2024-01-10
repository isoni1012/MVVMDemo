package com.example.mvvm_demo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_demo.databinding.ItemPhotoBinding
import com.example.mvvm_demo.model.PhotoModel

class PhotoListAdapter(private val context: Context, var list: ArrayList<PhotoModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val height = parent.measuredHeight / 4
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.root.minimumHeight = height

        return PhotoListVH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoListVH) {
            Glide.with(context).load(list[position].url).centerCrop().into(holder.binding.imgPhoto)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(result: List<PhotoModel>) {
        list.addAll(result)
        notifyDataSetChanged()
    }

    inner class PhotoListVH(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)
}