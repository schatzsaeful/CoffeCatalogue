package com.example.coffeecatalogue.module.coffee

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeecatalogue.databinding.ItemCoffeeBinding
import com.example.coffeecatalogue.model.viewparam.CoffeeViewParam

class CoffeeAdapter : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    private var coffeeHotData: MutableList<CoffeeViewParam.Data> = mutableListOf()

    inner class CoffeeViewHolder(private val binding: ItemCoffeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: CoffeeViewParam.Data) = with(binding) {
            var viewMore = false
            tvId.text = "${data.id}"
            tvTitle.text = data.title
            if (data.description.length > 100) {
                tvDescription.text = data.description.dropLast(data.description.length - 100)
            } else {
                tvDescription.text = data.description
            }
            tvShowMore.text = "Show More"
            tvShowMore.setOnClickListener {
                if (!viewMore) {
                    tvShowMore.text = "Show Less"
                    viewMore = true
                    tvDescription.text = data.description
                } else {
                    tvShowMore.text = "Show More"
                    viewMore = false
                    tvDescription.text = data.description.dropLast(data.description.length - 100)
                }
            }
            tvIngredient.text = getIngredient(data.ingredients)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        return CoffeeViewHolder(
            ItemCoffeeBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bind(coffeeHotData[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupList(coffeeList: List<CoffeeViewParam.Data>) {
        this.coffeeHotData.run {
            clear()
            addAll(coffeeList)
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = coffeeHotData.size

    private fun getIngredient(data: List<String>): String? {
        val coffeeIngredient: MutableList<String?> = ArrayList()
        for (ingredient in data) {
            coffeeIngredient.add(ingredient)
        }

        return TextUtils.join(", ", coffeeIngredient)
    }
}