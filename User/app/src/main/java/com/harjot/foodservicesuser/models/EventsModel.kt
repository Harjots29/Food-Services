package com.harjot.foodservicesuser.models

import android.media.Image

data class EventsModel(
    var id: String?=null,
    var uid: String?=null,
    var eventName: String?=null,
    var price: String?=null,
    var date: String?=null,
    var time: String?=null,
    var venue: String?=null,
    var image: Int?=null
)
