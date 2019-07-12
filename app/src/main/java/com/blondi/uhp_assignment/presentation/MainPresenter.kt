package com.blondi.uhp_assignment.presentation

import android.util.Log
import android.view.View
import com.blondi.uhp_assignment.models.response.RecipesResponse
import com.blondi.uhp_assignment.networking.interactors.Interactor
import com.blondi.uhp_assignment.ui.activities.MainContract
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainPresenter(private val interactor : Interactor): MainContract.Presenter
{
    private lateinit var  view: MainContract.View
    override fun setView(view: MainContract.View) {
        this.view=view
    }

    override fun onGetRecipes() {
        interactor.getRecipes(getRecipesCallback())
    }

    private fun getRecipesCallback(): retrofit2.Callback<RecipesResponse> = object : retrofit2.Callback<RecipesResponse>{
        override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
            view.onGetFailed(t.message.toString())

        }

        override fun onResponse(call: Call<RecipesResponse>, response: Response<RecipesResponse>) {
            view.onGetSuccessful(response.body()?.recipes)
        }

    }
}