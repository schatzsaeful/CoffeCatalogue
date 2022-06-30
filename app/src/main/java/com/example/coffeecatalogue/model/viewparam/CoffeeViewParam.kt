package com.example.coffeecatalogue.model.viewparam

class CoffeeViewParam : ArrayList<CoffeeViewParam.Data>() {
    data class Data(
        val description: String = "",
        val id: Int = 0,
        val image: String = "",
        val ingredients: List<String> = listOf(),
        val title: String = ""
    )
}