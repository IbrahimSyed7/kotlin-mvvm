package com.ims.coviddata.adapters

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.R
import com.ims.coviddata.databinding.ItemStatesBinding
import com.ims.coviddata.databinding.ItemTestsBinding
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested

class TestsRecyclerAdapter(
    val context: Context,
    val testedClickEvents: TestsRecyclerAdapter.TestedClickEvents
) : RecyclerView.Adapter<TestsRecyclerAdapter.TestsVH>() {

    var list = ArrayList<Tested>()
    var position = -1

    fun updateData(list: ArrayList<Tested>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestsRecyclerAdapter.TestsVH {
        val binding = ItemTestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestsVH(binding)
    }

    interface TestedClickEvents {
        fun onClickBookMark(tested: Tested)
        fun onDeleteBookMark(tested: Tested, position: Int)
    }

    override fun onBindViewHolder(holder: TestsRecyclerAdapter.TestsVH, position: Int) {

        val item = list[position]

        holder.bind.dailyTest.text =
            "Daily RTPCR samples :" + item.dailyrtpcrsamplescollectedicmrapplication
        holder.bind.reportedToday.text = "Samples reported today :" + item.samplereportedtoday
        holder.bind.totalDoses.text = "Total doses administered :" + item.totaldosesadministered

        holder.bind.bookmarkImgview.setOnClickListener {
            testedClickEvents.onClickBookMark(item)
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
                testedClickEvents.onDeleteBookMark(item, position)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TestsVH(private val binding: ItemTestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bind: ItemTestsBinding = this.binding
    }
}