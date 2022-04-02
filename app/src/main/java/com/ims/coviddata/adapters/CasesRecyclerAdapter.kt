package com.ims.coviddata.adapters

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.R
import com.ims.coviddata.databinding.ItemCasesBinding
import com.ims.coviddata.models.Cases

class CasesRecyclerAdapter(val context: Context, val casesClickEvents: CasesClickEvents) :
    RecyclerView.Adapter<CasesRecyclerAdapter.CasesVH>() {

    var list = ArrayList<Cases>()
    var position = -1
    fun updateData(list: ArrayList<Cases>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface CasesClickEvents {
        fun onClickBookMark(cases: Cases)
        fun onDeleteBookMark(cases: Cases,position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CasesRecyclerAdapter.CasesVH {
        val binding = ItemCasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CasesVH(binding)
    }

    override fun onBindViewHolder(holder: CasesRecyclerAdapter.CasesVH, position: Int) {
        val item = list[position]

        holder.bind.totalConfirmed.text = "Total Confirmed :" + item.totalconfirmed
        holder.bind.totalDeceases.text = "Total Deceased :" + item.totaldeceased
        holder.bind.totalRecovered.text = "Total Recovered :" + item.totalrecovered

        holder.bind.bookmarkImgview.setOnClickListener {
            casesClickEvents.onClickBookMark(item)
            this.position = position

            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)

        }
        if (this.position == position) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)

        } else {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_outlined)

        }

        //delete functionality
        if (context as Activity is BookMarkActivity) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.delete)

            holder.bind.bookmarkImgview.imageTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.red))

            holder.bind.bookmarkImgview.setOnClickListener {
                casesClickEvents.onDeleteBookMark(item,position)
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CasesVH(private val binding: ItemCasesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val bind: ItemCasesBinding = this.binding
    }
}