package com.example.aigame.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aigame.databinding.ItemShopCardBinding
import com.example.aigame.domain.ShopCardModel

class CardViewAdapter(data: List<ShopCardModel>, private val onClick:(Int, List<ShopCardModel>) -> Unit) :
    RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    private var list: List<ShopCardModel> = data
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CardViewHolder(
        val binding: ItemShopCardBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShopCardBinding.inflate(inflater, parent, false)

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.binding.ivShopCard.setImageResource(item.image)
        holder.binding.tvShopCardName.setText(item.name)
        holder.binding.tvShopCardMoney.text = item.cost.toString()

        holder.binding.imageView6.setOnClickListener {
            onClick(position, list)
        }
    }
}

