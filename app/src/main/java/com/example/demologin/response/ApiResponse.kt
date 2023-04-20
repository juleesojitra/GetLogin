package com.example.demologin.response

data class ApiResponse(
    val error: Any,
    val payload: List<Payload>,
    val status: Int
) {
    data class Payload(
        val catTitle: CatTitle,
        val category_status: String,
        val itemList: List<Item>,
        val item_category_id: String
    ) {
        data class CatTitle(
            val EN: String
        )

        data class Item(
            val add_ons: String,
            val allergy_info: String,
            val cuisine_id: String,
            val description: Description,
            val hold: Boolean,
            val image: String,
            val ingredients: Ingredients,
            val is_veg: Boolean,
            val item_id: String,
            val price: String,
            val resized_image: String,
            val status: String,
            val title: Title
        ) {
            data class AddOn(
                val __v: Int,
                val _id: String,
                val addon_id: String,
                val created_at: String,
                val deleted: Boolean,
                val image: String,
                val item_id: String,
                val price: Int,
                val title: String,
                val updated_at: String
            )

            data class Description(
                val EN: String
            )

            data class Ingredients(
                val EN: String
            )

            data class Title(
                val EN: String
            )
        }
    }
}