package com.djt.githubrepos.utils

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import okhttp3.RequestBody

import retrofit2.http.POST

import retrofit2.http.Multipart
import retrofit2.http.Body

interface WebServices {

    @GET("/repositories")
    @Headers("Content-Type: application/json")
    fun allitems(
    ): Call<ResponseBody>


}