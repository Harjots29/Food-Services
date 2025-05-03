package com.harjot.foodservicesuser.models

data class OrdersModel(
    var id: String?=null,
    var uid: String?=null,
    var item: String?=null,
    var price: String?=null,
    var quantity: String?=null,
    var itemType: String?=null,
    var location: String?=null,
)
