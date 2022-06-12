package com.djt.githubrepos

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.djt.githubrepos.utils.Constants
import com.djt.githubrepos.utils.WebServices
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONArray
import retrofit2.Retrofit


class TrendingRepo(var application: Application) {

    private var mErrorDataItem = MutableLiveData<String>()
    private var mSuccessItem = MutableLiveData<String>()

    private var observelist = MutableLiveData<ArrayList<trendinglist>>()



    fun getItem() {
        var webClient: Retrofit
        webClient = Constants.getWebClient()
        val services = webClient.create(WebServices::class.java)

        val call: Call<ResponseBody> = services.allitems()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.e(ContentValues.TAG, "ShopAssistOutputFragment: " + response.toString())

                    try {
                        val res = response.body()?.string()
                        val json = JSONArray(res)
                        var buitby=""
                        val arrylist = ArrayList<trendinglist>()
                       for(i in 0 until json.length()){
                           val jsonobj =json.getJSONObject(i)
                           val rank=jsonobj.getInt("rank")
                           val username=jsonobj.getString("username")
                           val reponame=jsonobj.getString("repositoryName")
                           val desc=jsonobj.getString("description")
                           val language=jsonobj.getString("language")
                           val stars=jsonobj.getInt("totalStars")
                           val jsonarray =jsonobj.getJSONArray("builtBy")
                           for(j in 0 until jsonarray.length()){
                               val jsonbuiltobj=jsonarray.getJSONObject(j)
                               buitby=jsonbuiltobj.getString("username")+","+buitby
                           }

                           arrylist.add(trendinglist(rank,username,reponame,desc,language, stars ,buitby))
                       }
                        observelist.setValue(arrylist)


                    } catch (e: Exception) {
                        e.printStackTrace()
                        mErrorDataItem.value = "Unable to fetch Item Details"
                    }
                } else {
                    mErrorDataItem.value = "Unable to fetch Item Details"
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                mErrorDataItem.value = application.getString(R.string.no_internet_connection)
            }

        })
    }


    fun observeErrorItem(): MutableLiveData<String> {
        return mErrorDataItem
    }

    fun observerSuccessItem(): MutableLiveData<String> {
        return mSuccessItem
    }

    fun observerSuccessList(): MutableLiveData<ArrayList<trendinglist>> {
        return observelist
    }



}