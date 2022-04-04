package com.ims.coviddata.fragments

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.MainActivity
import com.ims.coviddata.R
import com.ims.coviddata.adapters.CasesRecyclerAdapter
import com.ims.coviddata.databinding.DialogCasesInfoBinding
import com.ims.coviddata.databinding.FragmentCasesBinding
import com.ims.coviddata.models.Cases
import com.ims.coviddata.viewmodel.MainViewModel
import com.ims.coviddata.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.item_cases.*


class CasesFragment : Fragment() {

    lateinit var binding: FragmentCasesBinding

    lateinit var parentActivity: Activity

    lateinit var casesAdapter: CasesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCasesBinding.inflate(layoutInflater, container, false)

        parentActivity = requireActivity()

        setRecycler()
        //for remote data
        if (parentActivity is MainActivity) {
            (parentActivity as MainActivity).viewModel.casesData.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        casesAdapter.updateData(it.toList() as ArrayList<Cases>)
                    }

                })
        } else {

            (parentActivity as BookMarkActivity).viewModel.allCases().observe(viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        var casesArrayList = ArrayList<Cases>()
                        for (item in it.toList()) {
                            casesArrayList.add(item)
                        }
                        casesAdapter.updateData(casesArrayList)
                    }
                })
        }


        return binding.root
    }

    fun setRecycler() {
        casesAdapter = CasesRecyclerAdapter(context = requireContext(),
            object : CasesRecyclerAdapter.CasesClickEvents {
                override fun onClickBookMark(cases: Cases, isInsert: Boolean) {
                    if (cases.cases_time_series == null) {
                        cases.cases_time_series = ""
                    }
                    if (isInsert) {
                        (parentActivity as MainActivity).viewModel.insertCase(cases)
                    } else {
                        (parentActivity as MainActivity).viewModel.deleteCases(cases)
                    }
                }

                override fun onDeleteBookMark(cases: Cases, position: Int) {
                    (parentActivity as BookMarkActivity).viewModel.deleteCases(cases)

                    casesAdapter.notifyItemRemoved(position)


                }

                override fun onClickItem(cases: Cases) {
                    openDialog(cases)
                }

            })

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = casesAdapter
        }
    }

    fun openDialog(cases: Cases) {
        val dialog = Dialog(requireActivity())

        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogCasesInfoBinding.inflate(inflater)
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 600)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.setOnCancelListener {
            casesAdapter.notifyDataSetChanged()
        }

        if (cases.isInserted) {
            binding.bookmarkImgview.setImageResource(R.drawable.star_fill)

        } else {
            binding.bookmarkImgview.setImageResource(R.drawable.star_outlined)

        }

        binding.bookmarkImgview.setOnClickListener {

            if (cases.isInserted) {
                binding.bookmarkImgview.setImageResource(R.drawable.star_outlined)
                (parentActivity as MainActivity).viewModel.deleteCases(cases)

            } else {
                binding.bookmarkImgview.setImageResource(R.drawable.star_fill)
                (parentActivity as MainActivity).viewModel.insertCase(cases)

            }

        }

        binding.totalConfirmed.text = "Total Confirmed :" + cases.totalconfirmed
        binding.totalDeceases.text = "Total Deceased :" + cases.totaldeceased
        binding.totalRecovered.text = "Total Recovered :" + cases.totalrecovered
        binding.dailyConfirmed.text = "Daily Confirmed :" + cases.dailyconfirmed
        binding.dailyDeceased.text = "Daily Deceased :" + cases.dailydeceased
        binding.dailyRecovered.text = "Daily Recovered :" + cases.dailyrecovered
        binding.date.text = cases.date



        dialog.show()
    }

}