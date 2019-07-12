package com.blondi.uhp_assignment.networking.interactors

import com.blondi.uhp_assignment.models.response.RecipesResponse
import retrofit2.Callback

interface Interactor {
    fun getRecipes(responseCallback : Callback<RecipesResponse>)
}