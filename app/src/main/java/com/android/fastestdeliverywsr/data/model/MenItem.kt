package com.android.fastestdeliverywsr.data.model

data class MenItem(
    val id:Int,
    val name:String,
    val price:Int,
    val description:String,
    val images: List<Image>,
    val type: String
)

data class Image(
    val id:Int,
    val url:String
)