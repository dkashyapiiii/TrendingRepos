package com.djt.githubrepos.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


object Constants {


    const val url = "https://gh-trending-api.herokuapp.com"
    var retrofit: Retrofit? = null
    /**
     * Main Retrofit client
     */
    fun getWebClient(): Retrofit {
                  retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit!!
    }


    fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("Data", Context.MODE_PRIVATE)
    }

}