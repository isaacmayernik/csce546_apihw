package com.example.dogapicaller

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("id") val id: Id,
    @SerializedName("picture") val picture: Picture
)

data class Id(
    @SerializedName("id") val id: String
)

data class Picture(
    @SerializedName("url") val url: String
)