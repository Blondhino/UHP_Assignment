package com.blondi.uhp_assignment.networking

import com.blondi.uhp_assignment.models.response.RecipesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET("/api/search")
  fun  getRecipes(@Query("key") apiKey: String): Call<RecipesResponse>
}