package com.rm.mobile.data.network.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val specie: String,
    @SerializedName("image") val image: String,
)
