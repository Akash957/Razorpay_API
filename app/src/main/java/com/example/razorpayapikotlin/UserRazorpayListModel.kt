package com.example.razorpayapikotlin

data class UserRazorpayListModel(
    val count: Int? = null,
    val entity: String? = null,
    val items: List<Item>?=null

    )
data class Item(
    val id: String? = null,
    val name: String? = null,
    val entity: String? = null,
    val email: String? = null,
    val contact: String? = null,
    val crated_at:String? = null,
    val gstin:String? = null
)