package com.blondi.uhp_assignment.networking

import com.blondi.uhp_assignment.common.BASE_URL
import com.blondi.uhp_assignment.networking.interactors.Interactor
import com.blondi.uhp_assignment.networking.interactors.InteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendFactory {
    private var retrofit : Retrofit? = null
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient=
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    private val client : Retrofit? = if(retrofit==null) createRetrofit() else retrofit

    private fun createRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    private fun getService(): ApiService{
        return this.client!!.create(ApiService::class.java)
    }

    fun getInteractor(): Interactor {
        return InteractorImpl(getService())
    }

}