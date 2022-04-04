package com.ims.coviddata.adapters

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.R
import com.ims.coviddata.database.MainRoomDatabase
import com.ims.coviddata.databinding.ItemStatesBinding
import com.ims.coviddata.databinding.ItemTestsBinding
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested

class TestsRecyclerAdapter(
    val context: Context,
    val testedClickEvents: TestsRecyclerAdapter.TestedClickEvents
) : RecyclerView.Adapter<TestsRecyclerAdapter.TestsVH>() {

    var list = ArrayList<Tested>()

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
        fun onClickBookMark(tested: Tested, isInsert: Boolean)
        fun onDeleteBookMark(tested: Tested, position: Int)
        fun onClickSource(tested: Tested)
    }

    override fun onBindViewHolder(holder: TestsRecyclerAdapter.TestsVH, position: Int) {

        val item = list[position]

        holder.bind.dailyTest.text =
            "Daily RTPCR samples :" + item.dailyrtpcrsamplescollectedicmrapplication
        holder.bind.reportedToday.text = "Samples reported today :" + item.samplereportedtoday
        holder.bind.totalDoses.text = "Total doses administered :" + item.totaldosesadministered

        val bookmarkedCase = MainRoomDatabase.invoke(context).getMainDao().getTest(item.updatetimestamp)

        if (bookmarkedCase != null) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)
            item.isInserted = true

        } else {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.star_outlined)
        }

        holder.bind.bookmarkImgview.setOnClickListener {

            if (item.isInserted) {
                holder.bind.bookmarkImgview.setImageResource(R.drawable.star_outlined)
                testedClickEvents.onClickBookMark(item, isInsert = false)
                item.isInserted = false
            } else {
                holder.bind.bookmarkImgview.setImageResource(R.drawable.star_fill)
                testedClickEvents.onClickBookMark(item, isInsert = true)
                item.isInserted = true
            }

        }

        if (item.source!!.startsWith("https")) {
            holder.bind.source.visibility = VISIBLE
        } else {
            holder.bind.source.visibility = GONE
        }

        holder.bind.source.setOnClickListener {
            testedClickEvents.onClickSource(tested = item)
        }

        //delete functionality
        if (context as Activity is BookMarkActivity) {
            holder.bind.bookmarkImgview.setImageResource(R.drawable.delete)

            holder.bind.bookmarkImgview.imageTintList =
                ColorStateList.valueOf(context.resources.getColor(R.color.red))

            holder.bind.bookmarkImgview.setOnClickListener {
                list.removeAt(position)
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