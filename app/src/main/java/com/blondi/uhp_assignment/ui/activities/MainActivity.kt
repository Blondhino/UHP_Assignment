package com.blondi.uhp_assignment.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.opengl.Visibility
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.widget.LinearLayout
import android.widget.Toast
import com.blondi.uhp_assignment.ui.base.BaseActivity
import com.blondi.uhp_assignment.R
import com.blondi.uhp_assignment.adapters.RecipesAdapter
import com.blondi.uhp_assignment.models.response.RecipesResponse
import com.blondi.uhp_assignment.networking.BackendFactory
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity() {
    private val interactor = BackendFactory.getInteractor()
    private val adapter = RecipesAdapter()


    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun setUpUi() {
        tryGetRecipes()
        setUpRecycler()

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    private fun setUpRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun tryGetRecipes(){
        progressBar.visibility= View.VISIBLE
    if (isNetworkAvailable())
        interactor.getRecipes(getRecipesCallback())
    else {
        Toast.makeText(this, getString(R.string.WiFi_message), Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
    }
}

    private fun getRecipesCallback(): retrofit2.Callback<RecipesResponse> = object : retrofit2.Callback<RecipesResponse>{
        override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
            Log.e("Error","Error")

        }

        override fun onResponse(call: Call<RecipesResponse>, response: Response<RecipesResponse>) {
            progressBar.visibility= View.GONE
            adapter.setData(response.body()?.recipes)
        }

    }
    

}
