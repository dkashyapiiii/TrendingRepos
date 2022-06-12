package com.djt.githubrepos


import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.djt.githubrepos.databinding.ActivityMainBinding


class TrendingActivity : AppCompatActivity() {

    private var position: Int = -1
    private lateinit var binding: ActivityMainBinding
    lateinit var mViewModel: TrendingViewModel
    private lateinit var listadapter: TrendingAdapter
    private var repolist = ArrayList<trendinglist>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            val message = savedInstanceState.getInt("position")
            position = message
        }

        mViewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        mViewModel.getItemDetails()

        observerInit()
        initsearch()


    }

    private fun initsearch() {

        binding.repoSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listadapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listadapter.filter.filter(newText)
                return false
            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("position", position)
        super.onSaveInstanceState(outState)
    }

    private fun billinglisting() {


        binding.billingRv.apply {
            layoutManager = LinearLayoutManager(this@TrendingActivity)
            listadapter = TrendingAdapter(this@TrendingActivity, repolist, position)

            listadapter.setListener(object : TrendingAdapter.TrendingAdapterListner {

                override fun showdelievred(price: Int) {

                    position = price
                }

            })
            adapter = listadapter
            listadapter.notifyDataSetChanged()

        }
    }

    private fun observerInit() {
        mViewModel.observeErrorItem().observe(this, Observer {
            binding.mainProgress.visibility = View.GONE
        })
        mViewModel.observerSuccessList().observe(this, Observer {
            binding.mainProgress.visibility = View.GONE
            repolist = it
            billinglisting()

        })


    }


}

