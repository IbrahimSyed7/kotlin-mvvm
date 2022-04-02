package com.ims.coviddata.fragments

import android.app.Activity
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
import com.ims.coviddata.adapters.CasesRecyclerAdapter
import com.ims.coviddata.databinding.FragmentCasesBinding
import com.ims.coviddata.models.Cases
import com.ims.coviddata.viewmodel.MainViewModel
import com.ims.coviddata.viewmodel.ViewModelFactory


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
                override fun onClickBookMark(cases: Cases) {
                    if (cases.cases_time_series == null) {
                        cases.cases_time_series = ""
                    }

                    (parentActivity as MainActivity).viewModel.insertCase(cases)
                }

                override fun onDeleteBookMark(cases: Cases, position: Int) {
                    (parentActivity as BookMarkActivity).viewModel.deleteCases(cases)

                    casesAdapter.notifyItemRemoved(position)


                }

            })

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = casesAdapter
        }
    }

}