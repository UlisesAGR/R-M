package com.rm.mobile.data.network.response

import com.google.gson.annotations.SerializedName

data class CharactersDataResponse(
    @SerializedName("info") var info: InfoResponse,
    @SerializedName("results") var results: List<CharacterResponse>,
)
