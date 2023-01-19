package com.z0o0a.variouslayout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.z0o0a.variouslayout.databinding.RecyclerviewItemBinding
import com.z0o0a.variouslayout.model.Banner

class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.BannerViewHolder>(){
    private var bannerList = ArrayList<Banner>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerList[position])
    }

    override fun getItemCount() = bannerList.size

    inner class BannerViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentBanner : Banner) {
            binding.banner = currentBanner
        }
    }

    fun setDrinkList(banners:ArrayList<Banner>){
        this.bannerList = banners
        notifyDataSetChanged()
    }
}