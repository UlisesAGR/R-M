/*
 * RecipesServices.kt
 * Created by Ulises Gonzalez on 25/02/24
 * Copyright (c) 2023. All rights reserved.
 */
package com.rm.mobile.data.network.service

import com.rm.mobile.BuildConfig.CHARACTER
import com.rm.mobile.data.network.response.CharacterResponse
import com.rm.mobile.data.network.response.CharactersDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterServices {
    @GET(CHARACTER)
    suspend fun getCharacters(
        @Query("page") page: Int,
    ): CharactersDataResponse

    @GET("$CHARACTER/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): Response<CharacterResponse>
}
