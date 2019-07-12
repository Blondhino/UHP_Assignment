package com.blondi.uhp_assignment.ui.activities

import com.blondi.uhp_assignment.models.Recipes

interface MainContract {

    interface View{
        fun onGetSuccessful(respone: MutableList<Recipes>?)
        fun onGetFailed(errorMessage : String)
    }

    interface Presenter{

        fun setView(view : MainContract.View)
        fun onGetRecipes()
    }

}