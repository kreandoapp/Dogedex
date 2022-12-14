package com.example.dogedex.doglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogedex.model.Dog
import com.example.dogedex.databinding.DogListItemBinding

class DogAdapter : ListAdapter<Dog, DogAdapter.DogViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Dog>(){
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id == newItem.id
        }

    }

    private var onItemClickListener : ((Dog) -> Unit) ? = null

    fun setOnItemClickLister(onItemClickListener : (Dog) -> Unit){
        this.onItemClickListener = onItemClickListener
    }
    private var onLongItemClickListener : ((Dog) -> Unit) ? = null
    fun setonLongItemClickLister(onLongItemClickListener : (Dog) -> Unit){
        this.onLongItemClickListener = onLongItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogListItemBinding.inflate(LayoutInflater.from(parent.context))

       return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.bind(dog)
    }

    inner class DogViewHolder(private val binding : DogListItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(dog : Dog){

            binding.dogListItemLayout.setOnClickListener {
                onItemClickListener?.invoke(dog)
            }
            binding.dogListItemLayout.setOnLongClickListener {
                onLongItemClickListener?.invoke(dog)
                true
            }
            binding.dogImage.load(dog.imageUrl)
        }
    }


}