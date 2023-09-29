package com.example.aigame.ui.medium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.aigame.databinding.ItemCollectionCardBinding
import com.example.aigame.databinding.ItemMediumRoomBinding
import com.example.aigame.databinding.ItemShopCardBinding
import com.example.aigame.databinding.ItemSmallRoomBinding
import com.example.aigame.domain.CollectionCardModel
import com.example.aigame.domain.RoomCardModel
import com.example.aigame.domain.ShopCardModel

class MediumViewAdapter(data: List<RoomCardModel>, private val onClick:(Int, List<RoomCardModel>) -> Unit) :
    RecyclerView.Adapter<MediumViewAdapter.CardViewHolder>() {
    private var list: List<RoomCardModel> = data
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class CardViewHolder(
        val binding: ItemMediumRoomBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMediumRoomBinding.inflate(inflater, parent, false)

        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = list[position]

        holder.binding.ivItemMedium.setImageResource(item.image)
        holder.binding.ivBackgroundMedium.setImageResource(item.background)

        holder.binding.ivBackgroundMedium.setOnClickListener {
            onClick(position, list)
        }
    }
}

