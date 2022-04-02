package com.ims.coviddata.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.MainActivity
import com.ims.coviddata.adapters.StatesRecyclerAdapter
import com.ims.coviddata.databinding.FragmentStatesBinding
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.Statewise

class StatesFragment : Fragment() {


    lateinit var binding: FragmentStatesBinding

    lateinit var parentActivity: Activity

    lateinit var statesAdapter: StatesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatesBinding.inflate(layoutInflater, container, false)
        parentActivity = requireActivity()

        setRecycler()
        if (parentActivity is MainActivity) {
            (parentActivity as MainActivity).viewModel.stateWiseData.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        statesAdapter.updateData(it.toList() as ArrayList<Statewise>)
                    }
                })
        } else {
            (parentActivity as BookMarkActivity).viewModel.allStates().observe(viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        var statesArrayList = ArrayList<Statewise>()
                        for (item in it.toList()) {
                            statesArrayList.add(item)
                        }
                        statesAdapter.updateData(statesArrayList)
                    }
                })
        }



        return binding.root
    }

    fun setRecycler() {
        statesAdapter = StatesRecyclerAdapter(context = requireContext(),object :StatesRecyclerAdapter.StatesClickEvents{
            override fun onClickBookMark(statewise: Statewise) {
                (parentActivity as MainActivity).viewModel.insertState(statewise)

            }

            override fun onDeleteBookMark(statewise: Statewise,position : Int) {
                (parentActivity as BookMarkActivity).viewModel.deleteState(statewise)
                statesAdapter.notifyItemRemoved(position)

            }

        })

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = statesAdapter
        }
    }


}