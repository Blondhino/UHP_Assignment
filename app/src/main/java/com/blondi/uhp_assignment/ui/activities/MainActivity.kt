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
import com.blondi.uhp_assignment.models.Recipes
import com.blondi.uhp_assignment.models.response.RecipesResponse
import com.blondi.uhp_assignment.networking.BackendFactory
import com.blondi.uhp_assignment.presentation.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity(), MainContract.View {

    private val adapter = RecipesAdapter()
    private val presenter = MainPresenter(BackendFactory.getInteractor())

    override fun onGetFailed(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }
    override fun onGetSuccessful(respone: MutableList<Recipes>?) {
        adapter.setData(respone)
        progressBar.visibility= View.GONE
    }
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }
    override fun setUpUi() {
        tryGetRecipes()
        setUpRecycler()
        presenter.setView(this)

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
        presenter.onGetRecipes()
    else {
        Toast.makeText(this, getString(R.string.WiFi_message), Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
    }
}




}
