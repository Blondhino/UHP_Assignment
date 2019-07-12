package com.blondi.uhp_assignment.networking.interactors

import com.blondi.uhp_assignment.common.KEY_AUTHORIZATION
import com.blondi.uhp_assignment.models.response.RecipesResponse
import com.blondi.uhp_assignment.networking.ApiService
import retrofit2.Callback

class InteractorImpl(private val apiService: ApiService):
    Interactor {
    override fun getRecipes(responseCallback: Callback<RecipesResponse>) {
        apiService.getRecipes(KEY_AUTHORIZATION).enqueue(responseCallback)
    }
}