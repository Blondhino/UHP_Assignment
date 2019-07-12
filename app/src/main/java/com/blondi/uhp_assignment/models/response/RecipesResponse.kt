package com.blondi.uhp_assignment.models.response

import com.blondi.uhp_assignment.models.Recipes

data class RecipesResponse (val count: Int, val recipes : MutableList<Recipes>?= mutableListOf())