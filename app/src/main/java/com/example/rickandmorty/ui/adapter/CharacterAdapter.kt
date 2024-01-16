package com.example.rickandmorty.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.model.Result

class CharacterAdapter(private val nav:(Bundle)->Unit):RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

     private val diffCall = object : DiffUtil.ItemCallback<Result>() {
         override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
             return oldItem===newItem
         }

         override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
             return oldItem==newItem
         }

     }

    private val diffUtil = AsyncListDiffer(this,diffCall)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }


    inner class ViewHolder(private val binding:ItemCharacterBinding):RecyclerView.ViewHolder(binding.root){
          fun bind(current:Result){
              binding.txtName.text = current.name
              Glide.with(binding.root)
                  .load(current.image)
                  .into(binding.imgCharacter)

              binding.imgGender.setImageResource(if (current.gender=="Male") R.drawable.male_sign else if(current.gender=="Female") R.drawable.female_sign else R.drawable.unknown_sign )

              itemView.setOnClickListener {
                  val bundle = Bundle()
                  bundle.putInt("CharId",current.id)
                  nav(bundle)
              }
          }


    }

    fun submitList(list:List<Result>){
        diffUtil.submitList(list)
    }

}