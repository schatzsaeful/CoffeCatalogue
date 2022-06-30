package com.example.coffeecatalogue.model.entity

class CoffeeEntity : ArrayList<CoffeeEntity.Data>() {
    data class Data(
        val description: String?,
        val id: Int?,
        val image: String?,
        val ingredients: List<String>?,
        val title: String?
    )
}