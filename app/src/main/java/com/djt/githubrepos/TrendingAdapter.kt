package com.djt.githubrepos

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class TrendingAdapter(
    var context: Context,
    private var repolist: List<trendinglist>,
    position: Int,

    ) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() ,Filterable{
    var FilterList = ArrayList<trendinglist>()
    var selectedPosition = position
    private var listener: TrendingAdapterListner? = null

    init {
        FilterList = repolist as ArrayList<trendinglist>
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.trending_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TrendingViewHolder -> {
                addview(holder, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return FilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) FilterList = repolist as ArrayList<trendinglist>
                else {
                    val filteredList = ArrayList<trendinglist>()

//                    for (s in repolist) {
//                        if (s.description.toLowerCase().contains(charString) || s.getText2().toLowerCase()
//                                .contains(text)
//                        ) {
//                            filterdNames.add(s)
//                        }
//                    }
                    repolist.filter {
                        (it.description.toLowerCase().contains(charString!!)) or
                                (it.username.toLowerCase().contains(charString)) or
                                (it.reponame.toLowerCase().contains(charString)) or
                                (it.language.toLowerCase().contains(charString))


                    }
                        .forEach { filteredList.add(it) }
                    FilterList = filteredList

                }
                return FilterResults().apply { values = FilterList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                FilterList = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<trendinglist>
                notifyDataSetChanged()
            }
        }
    }

    inner class TrendingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rank: TextView = view.findViewById(R.id.rank)
        var user: TextView = view.findViewById(R.id.username)
        var repo: TextView = view.findViewById(R.id.reponame)
        var desc: TextView = view.findViewById(R.id.description)
        var stars: TextView = view.findViewById(R.id.starts)
        var lang: TextView = view.findViewById(R.id.language)
        var builtby: TextView = view.findViewById(R.id.builtby)

    }

    private fun addview(holder: TrendingAdapter.TrendingViewHolder, position: Int) {


        val listitem = FilterList?.get(position)
        holder.desc.text = listitem.description
        holder.stars.text = listitem.totalStars.toString() + "   "
        holder.repo.text = listitem.reponame
        holder.user.text = listitem.username + "/ "
        holder.builtby.text = listitem.builtby
        holder.rank.text = "#" + listitem.rank.toString()


        if (listitem.language.equals("null")) {
            holder.lang.visibility = View.GONE
        } else {
            holder.lang.text = " " + listitem.language
        }


        if (selectedPosition === position)
            holder.itemView.setBackgroundColor(Color.parseColor("#4E1495FB"))
        else holder.itemView.setBackgroundColor(
            Color.parseColor("#ffffff")
        )


        holder.itemView.setOnClickListener {
            selectedPosition = position
            listener!!.showdelievred(position)
            notifyDataSetChanged()
        }
    }

    fun setListener(listener: TrendingAdapterListner) {
        this.listener = listener
    }

    interface TrendingAdapterListner {

        fun showdelievred(
            price: Int,
        )
    }


}
