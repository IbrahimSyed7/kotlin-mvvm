package com.ims.coviddata.fragments

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
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.MainActivity
import com.ims.coviddata.R
import com.ims.coviddata.adapters.StatesRecyclerAdapter
import com.ims.coviddata.databinding.DialogStatesInfoBinding
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
        statesAdapter = StatesRecyclerAdapter(context = requireContext(),
            object : StatesRecyclerAdapter.StatesClickEvents {
                override fun onClickBookMark(statewise: Statewise, isInsert: Boolean) {
                    if (isInsert) {
                        (parentActivity as MainActivity).viewModel.insertState(statewise)
                    } else {
                        (parentActivity as MainActivity).viewModel.deleteState(statewise)
                    }

                }

                override fun onDeleteBookMark(statewise: Statewise, position: Int) {
                    (parentActivity as BookMarkActivity).viewModel.deleteState(statewise)
                    statesAdapter.notifyItemRemoved(position)

                }

                override fun onClickItem(statewise: Statewise) {
                    openDialog(statewise)
                }

            })

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = statesAdapter
        }
    }

    fun openDialog(statewise: Statewise) {
        val dialog = Dialog(requireActivity())

        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogStatesInfoBinding.inflate(inflater)
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 800)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.setOnCancelListener {
            statesAdapter.notifyDataSetChanged()
        }

        if (statewise.isInserted) {
            binding.bookmarkImgview.setImageResource(R.drawable.star_fill)

        } else {
            binding.bookmarkImgview.setImageResource(R.drawable.star_outlined)

        }

        binding.bookmarkImgview.setOnClickListener {

            if (statewise.isInserted) {
                binding.bookmarkImgview.setImageResource(R.drawable.star_outlined)
                (parentActivity as MainActivity).viewModel.deleteState(statewise)

            } else {
                binding.bookmarkImgview.setImageResource(R.drawable.star_fill)
                (parentActivity as MainActivity).viewModel.insertState(statewise)
            }

        }

        binding.state.text = "State :" + statewise.state
        binding.active.text = "Active :" + statewise.active
        binding.deaths.text = "Deceased :" + statewise.deaths
        binding.totalRecovered.text = "Recovered :" + statewise.recovered
        binding.deltaConfirm.text = "Delta confirm :" + statewise.deltaconfirmed
        binding.deltaDeaths.text = "Delta deaths :" + statewise.deltadeaths
        binding.deltaRecover.text = "Delta recover :" + statewise.deltarecovered



        dialog.show()
    }
}