package com.example.aigame.ui.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.aigame.databinding.ItemCollectionCardBinding
import com.example.aigame.databinding.ItemShopCardBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.domain.ShopCardModel

class CollectionViewAdapter(data: List<CollectionCardModel>, private val onClick:(Int, List<CollectionCardModel>) -> Unit) :
    RecyclerView.Adapter<CollectionViewAdapter.CardViewHolder>() {
    private var list: List<CollectionCardModel> = data
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CardViewHolder(
        val binding: ItemCollectionCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCollectionCardBinding.inflate(inflater, parent, false)

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.binding.ivCollectionCard.setImageResource(item.image)
        holder.binding.tvCollectionCardName.setText(item.name)

        holder.binding.imageView18.setOnClickListener {
            onClick(position, list)
        }
    }
}

