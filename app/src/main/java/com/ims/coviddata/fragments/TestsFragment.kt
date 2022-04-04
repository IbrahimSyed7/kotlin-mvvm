package com.ims.coviddata.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ims.coviddata.BookMarkActivity
import com.ims.coviddata.MainActivity
import com.ims.coviddata.adapters.StatesRecyclerAdapter
import com.ims.coviddata.adapters.TestsRecyclerAdapter
import com.ims.coviddata.databinding.FragmentTestsBinding
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested
import com.ims.coviddata.utils.Utils

class TestsFragment : Fragment() {


    lateinit var binding: FragmentTestsBinding

    lateinit var parentActivity: Activity

    lateinit var testAdapter: TestsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestsBinding.inflate(layoutInflater, container, false)
        parentActivity = requireActivity()
        setRecycler()

        if (parentActivity is MainActivity) {
            (parentActivity as MainActivity).viewModel.testedData.observe(
                viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        testAdapter.updateData(it.toList() as ArrayList<Tested>)
                    }
                })
        } else {
            (parentActivity as BookMarkActivity).viewModel.allTests().observe(
                viewLifecycleOwner,
                Observer {
                    if (it.isNotEmpty()) {
                        var testArrayList = ArrayList<Tested>()
                        for (item in it.toList()) {
                            testArrayList.add(item)
                        }
                        testAdapter.updateData(testArrayList)
                    }
                })
        }



        return binding.root
    }

    fun setRecycler() {

        testAdapter = TestsRecyclerAdapter(context = requireContext(),
            object : TestsRecyclerAdapter.TestedClickEvents {
                override fun onClickBookMark(tested: Tested,isInsert : Boolean) {
                    if (isInsert) {
                        (parentActivity as MainActivity).viewModel.insertTest(tested)
                    } else {
                        (parentActivity as MainActivity).viewModel.deleteTest(tested)
                    }
                }

                override fun onDeleteBookMark(tested: Tested, position: Int) {
                    (parentActivity as BookMarkActivity).viewModel.deleteTest(tested)
                    testAdapter.notifyItemRemoved(position)
                }

                override fun onClickSource(tested: Tested) {
                    if (Utils.checkForInternet(requireContext())) {
                        val openURL = Intent(Intent.ACTION_VIEW)
                        openURL.data = Uri.parse(tested.source)
                        startActivity(openURL)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Internet not available",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            })

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = testAdapter
        }

    }


}