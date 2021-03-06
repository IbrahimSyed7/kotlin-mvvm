package com.ims.coviddata.adapters

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.R
import com.ims.coviddata.database.MainRoomDatabase
import com.ims.coviddata.databinding.ItemCasesBinding
import com.ims.coviddata.databinding.ItemStatesBinding
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.Statewise

class StatesRecyclerAdapter(val context: Context, val statesClickEvents: StatesClickEvents) : RecyclerView.Adapter<StatesRecyclerAdapter.StatesVH>() {

    var list = ArrayList<Statewise>()

    fun updateData(list: ArrayList<Statewise>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatesRecyclerAdapter.StatesVH {
        val binding = ItemStatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatesVH(binding)
    }

    interface StatesClickEvents {
        fun onClickBookMark(statewise: Statewise,isInsert : Boolean)
        fun onDeleteBookMark(statewise: Statewise,position: Int)
        fun onClickItem(statewise: Statewise)
    }

    override fun onBindViewHolder(holder: StatesRecyclerAdapter.StatesVH, position: Int) {

        val item = list[position]

        holder.bind.state.text = "State :" + item.state
        holder.bind.active.text = "Active :" + item.active
        holder.bind.deaths.text = "Deceased :" + item.deaths
        holder.bind.totalRecovered.text = "Recovered :" + item.recovered

        if (item.state!!.lowercase().contains("total")) {
            holder.bind.state.text = item.state
        }

        val bookmarkedCase = MainRoomDatabase.invoke(context).getMainDao().getStates(item.state)

        if (bookmarkedCase != null) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)
            item.isInserted = true

        } else {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_outlined)
        }

        holder.bind.bookmarkImgview.setOnClickListener {

            if (item.isInserted)
            {
                holder.bind.bookmarkImgview.setImageResource(R.drawable.star_outlined)
                statesClickEvents.onClickBookMark(item,isInsert = false)
                item.isInserted = false
            }
            else
            {
                holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)
                statesClickEvents.onClickBookMark(item,isInsert = true)
                item.isInserted = true
            }

        }


        holder.bind.root.setOnClickListener {
            statesClickEvents.onClickItem(statewise = item)
        }

        //delete functionality
        if (context as Activity is BookMarkActivity) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.delete)
            holder.bind.bookmarkImgview.imageTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.red))

            holder.bind.bookmarkImgview.setOnClickListener {
                list.removeAt(position)
                statesClickEvents.onDeleteBookMark(item,position)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class StatesVH(private val binding: ItemStatesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bind: ItemStatesBinding = this.binding
    }
}